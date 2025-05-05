package com.axity.ticket.facade;

import com.axity.ticket.commons.dto.TicketDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Optional;

public interface TicketFacade {
    void publishTicket(TicketDto ticket) throws JsonProcessingException;
    TicketDto getbyId(Long skId) throws JsonProcessingException;
    List<TicketDto> getAll();
    void update(Long skId, TicketDto ticket);
    void delete(Long skId);
}