package com.axity.ticket.service;

import com.axity.ticket.commons.dto.TicketDto;

import java.util.List;
import java.util.Optional;

public interface TicketService {
  void save(TicketDto ticket);
  Optional<TicketDto> getById(Long skId);
  List<TicketDto> getAllTickets();
  void delete(Long skId);

}