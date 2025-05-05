package com.axity.ticket.facade.impl;

import com.axity.ticket.commons.dto.TicketDto;
import com.axity.ticket.facade.TicketFacade;
import com.axity.ticket.service.TicketService;
import com.axity.ticket.service.producer.TicketProducerService;
import com.axity.ticket.service.redis.RedisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class TicketFacadeImpl implements TicketFacade {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketProducerService ticketProducerService;

    @Autowired
    private RedisService redisService;

    @Override
    public void publishTicket(TicketDto ticket) throws JsonProcessingException {
        ticketProducerService.send(ticket);
    }

    @Override
    public TicketDto getbyId(Long skId) throws JsonProcessingException {
        Optional<TicketDto> ticket = redisService.get(String.valueOf(skId), TicketDto.class);
        if (ticket.isPresent()) {
            log.info( "Se encontró en redis");
            return ticket.get();
        }

        Optional<TicketDto> document = ticketService.getById(skId);
        if (document.isPresent()) {
            log.info( "Se encontró en mongo");
            redisService.save(String.valueOf(skId), document.get());
            return document.get();
        }
        throw new EntityNotFoundException("Ticket no encontrado: " + skId);
    }

    @Override
    public List<TicketDto> getAll() {
        return ticketService.getAllTickets();
    }

    @Override
    public void update(Long skId, TicketDto ticket) {
        ticket.setSkId(skId);
        ticketService.save(ticket);
    }

    @Override
    public void delete(Long skId) {
        ticketService.delete(skId);
    }
}