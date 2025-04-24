package com.axity.ticket.service;

import com.axity.ticket.commons.dto.TicketDto;

import java.util.List;

public interface TicketService {
  TicketDto crearNuevoTicket(TicketDto ticket);
  TicketDto obtenerTicket(Long skId);
  List<TicketDto> obtenerTodosLosTickets();
  boolean actualizarTicketExistente(TicketDto ticket);
  boolean eliminarTicket(Long skId);
}