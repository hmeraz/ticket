package com.axity.ticket.facade.impl;

import com.axity.ticket.commons.dto.TicketDto;
import com.axity.ticket.facade.TicketFacade;
import com.axity.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketFacadeImpl implements TicketFacade {

    @Autowired
    private TicketService ticketService;

    @Override
    public TicketDto crearTicket(TicketDto ticket) {
        return ticketService.crearNuevoTicket(ticket);
    }

    @Override
    public TicketDto obtenerTicket(Long skId) {
        return ticketService.obtenerTicket(skId);
    }

    @Override
    public List<TicketDto> obtenerTodosLosTickets() {
        return ticketService.obtenerTodosLosTickets();
    }

    @Override
    public boolean actualizarTicket(Long skId, TicketDto ticket) {
        ticket.setSkId(skId);
        return ticketService.actualizarTicketExistente(ticket);
    }

    @Override
    public boolean eliminarTicket(Long skId) {
        return ticketService.eliminarTicket(skId);
    }
}