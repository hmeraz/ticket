package com.axity.ticket.service.impl;
import com.axity.ticket.commons.dto.TicketDto;
import com.axity.ticket.model.TicketDocument;
import com.axity.ticket.persistence.TicketRepository;
import com.axity.ticket.service.TicketService;
import com.axity.ticket.service.mapper.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    public TicketServiceImpl(TicketRepository ticketRepository, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
    }

    public void save(TicketDto ticket) {
        TicketDocument doc = ticketMapper.toDocument(ticket);
        ticketRepository.save(doc);
    }

    public Optional<TicketDto> getById(Long skId) {
        return ticketRepository.findById(skId)
                .map(ticketMapper::toDto);
    }

    public List<TicketDto> getAllTickets() {
        List<TicketDocument> docs = ticketRepository.findAll();
        return ticketMapper.toDtoList(docs);
    }

    public void delete(Long skId) {
        ticketRepository.deleteById(skId);
    }
}