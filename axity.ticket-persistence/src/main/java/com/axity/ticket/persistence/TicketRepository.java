package com.axity.ticket.persistence;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.axity.ticket.model.TicketDocument;

@Repository
public interface TicketRepository extends CassandraRepository<TicketDocument, Long> {
    // Optional<TicketDocument> findByDxUuid(String dxUuid);
}