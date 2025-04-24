package com.axity.ticket.facade;

import com.axity.ticket.commons.dto.TicketDto;

import java.util.List;

public interface TicketFacade {
    TicketDto crearTicket(TicketDto ticket);
    TicketDto obtenerTicket(Long skId);
    List<TicketDto> obtenerTodosLosTickets();
    boolean actualizarTicket(Long skId, TicketDto ticket);
    boolean eliminarTicket(Long skId);
}