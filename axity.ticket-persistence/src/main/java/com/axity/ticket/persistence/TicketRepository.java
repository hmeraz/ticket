package com.axity.ticket.persistence;
import org.springframework.stereotype.Repository;

import com.axity.ticket.model.TicketDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface TicketRepository extends MongoRepository<TicketDocument, Long> {
    // Optional<TicketDocument> findByDxUuid(String dxUuid);
}