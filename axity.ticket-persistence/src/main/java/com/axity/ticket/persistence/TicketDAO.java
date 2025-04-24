package com.axity.ticket.persistence;

import com.axity.ticket.commons.dto.TicketDto;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Repository
public class TicketDAO {

    private MongoClient mongoClient;
    private MongoCollection<Document> collection;

    @Value("${spring.data.mongodb.uri}")
    private String uri;

    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Value("${spring.data.mongodb.collection}")
    private String collectionName;

    @PostConstruct
    public void initialize() {
        this.mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        this.collection = database.getCollection(collectionName);
    }

    protected MongoCollection<Document> getCollection() {
        return collection;
    }

    public TicketDto crearTicket(TicketDto ticket) {
        Document doc = mapTicketDtoToDocument(ticket);
        InsertOneResult result = collection.insertOne(doc);
        if (result.wasAcknowledged() && result.getInsertedId() != null) {
            return ticket;
        } else {
            return null;
        }
    }

    public TicketDto obtenerTicketPorSkId(Long skId) {
        Document doc = collection.find(Filters.eq("skId", skId)).first();
        if (doc != null) {
            return mapDocumentToTicketDto(doc);
        }
        return null;
    }

    public List<TicketDto> obtenerTodosLosTickets() {
        List<TicketDto> tickets = new ArrayList<>();
        collection.find().forEach((Consumer<Document>) doc -> tickets.add(mapDocumentToTicketDto(doc)));
        return tickets;
    }

    public boolean actualizarTicket(TicketDto ticket) {
        Bson updates = crearUpdates(ticket);
        UpdateResult result = collection.updateOne(Filters.eq("skId", ticket.getSkId()), updates);
        return result.wasAcknowledged() && result.getModifiedCount() > 0;
    }

    public boolean eliminarTicketPorSkId(Long skId) {
        DeleteResult result = collection.deleteOne(Filters.eq("skId", skId));
        return result.wasAcknowledged() && result.getDeletedCount() > 0;
    }

    @PreDestroy
    public void cerrarConexion() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

    private Document mapTicketDtoToDocument(TicketDto ticket) {
        return new Document()
                .append("skId", ticket.getSkId())
                .append("dxTcn", ticket.getDxTcn())
                .append("dxDigital", ticket.getDxDigital())
                .append("dxEmail", ticket.getDxEmail())
                .append("dxPhone", ticket.getDxPhone())
                .append("dxTicket", ticket.getDxTicket())
                .append("dxPrinted", ticket.getDxPrinted())
                .append("dxStoreFormat", ticket.getDxStoreFormat())
                .append("dxDate", ticket.getDxDate())
                .append("dxTime", ticket.getDxTime())
                .append("dxTyc", ticket.getDxTyc())
                .append("dxStoreName", ticket.getDxStoreName())
                .append("dxTda", ticket.getDxTda())
                .append("dxOp", ticket.getDxOp())
                .append("dxTe", ticket.getDxTe())
                .append("dxTr", ticket.getDxTr())
                .append("dxStoreNumber", ticket.getDxStoreNumber())
                .append("dxUuid", ticket.getDxUuid());
    }

    private TicketDto mapDocumentToTicketDto(Document doc) {
        TicketDto ticket = new TicketDto();
        ticket.setSkId(doc.getLong("skId"));
        ticket.setDxTcn(doc.getString("dxTcn"));
        Integer dxDigital = doc.getInteger("dxDigital");
        if (dxDigital != null) ticket.setDxDigital(dxDigital);
        ticket.setDxEmail(doc.getString("dxEmail"));
        Long dxPhone = doc.getLong("dxPhone");
        if (dxPhone != null) ticket.setDxPhone(dxPhone);
        ticket.setDxTicket(doc.getString("dxTicket"));
        Integer dxPrinted = doc.getInteger("dxPrinted");
        if (dxPrinted != null) ticket.setDxPrinted(dxPrinted);
        ticket.setDxStoreFormat(doc.getString("dxStoreFormat"));
        java.util.Date dxDateUtil = doc.getDate("dxDate");
        if (dxDateUtil != null) ticket.setDxDate(dxDateUtil.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
        Object dxTimeObject = doc.get("dxTime");
        if (dxTimeObject != null) {
            if (dxTimeObject instanceof java.util.Date) {
                java.util.Date dxTimeUtil = (java.util.Date) dxTimeObject;
                ticket.setDxTime(dxTimeUtil.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime().withNano(0));
            } else if (dxTimeObject instanceof String) {
                ticket.setDxTime(LocalTime.parse((String) dxTimeObject));
            }
        }
        Integer dxTyc = doc.getInteger("dxTyc");
        if (dxTyc != null) ticket.setDxTyc(dxTyc);
        ticket.setDxStoreName(doc.getString("dxStoreName"));
        Integer dxTda = doc.getInteger("dxTda");
        if (dxTda != null) ticket.setDxTda(dxTda);
        Long dxOp = doc.getLong("dxOp");
        if (dxOp != null) ticket.setDxOp(dxOp);
        Integer dxTe = doc.getInteger("dxTe");
        if (dxTe != null) ticket.setDxTe(dxTe);
        Integer dxTr = doc.getInteger("dxTr");
        if (dxTr != null) ticket.setDxTr(dxTr);
        Integer dxStoreNumber = doc.getInteger("dxStoreNumber");
        if (dxStoreNumber != null) ticket.setDxStoreNumber(dxStoreNumber);
        ticket.setDxUuid(doc.getString("dxUuid"));
        return ticket;
    }

    private Bson crearUpdates(TicketDto ticket) {
        List<Bson> updates = new ArrayList<>();
        updates.add(Updates.set("dxTcn", ticket.getDxTcn()));
        updates.add(Updates.set("dxDigital", ticket.getDxDigital()));
        updates.add(Updates.set("dxEmail", ticket.getDxEmail()));
        updates.add(Updates.set("dxPhone", ticket.getDxPhone()));
        updates.add(Updates.set("dxTicket", ticket.getDxTicket()));
        updates.add(Updates.set("dxPrinted", ticket.getDxPrinted()));
        updates.add(Updates.set("dxStoreFormat", ticket.getDxStoreFormat()));
        updates.add(Updates.set("dxDate", ticket.getDxDate()));
        updates.add(Updates.set("dxTime", ticket.getDxTime()));
        updates.add(Updates.set("dxTyc", ticket.getDxTyc()));
        updates.add(Updates.set("dxStoreName", ticket.getDxStoreName()));
        updates.add(Updates.set("dxTda", ticket.getDxTda()));
        updates.add(Updates.set("dxOp", ticket.getDxOp()));
        updates.add(Updates.set("dxTe", ticket.getDxTe()));
        updates.add(Updates.set("dxTr", ticket.getDxTr()));
        updates.add(Updates.set("dxStoreNumber", ticket.getDxStoreNumber()));
        updates.add(Updates.set("dxUuid", ticket.getDxUuid()));
        return Updates.combine(updates);
    }
}