package com.axity.ticket.service.impl;
import com.axity.ticket.commons.dto.TicketDto;
import com.axity.ticket.persistence.TicketDAO;
import com.axity.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDAO ticketDAO;

    public TicketDto crearNuevoTicket(TicketDto ticket) {
        return ticketDAO.crearTicket(ticket);
    }

    public TicketDto obtenerTicket(Long skId) {
        return ticketDAO.obtenerTicketPorSkId(skId);
    }

    public List<TicketDto> obtenerTodosLosTickets() {
        return ticketDAO.obtenerTodosLosTickets();
    }

    public boolean actualizarTicketExistente(TicketDto ticket) {
        return ticketDAO.actualizarTicket(ticket);
    }

    public boolean eliminarTicket(Long skId) {
        return ticketDAO.eliminarTicketPorSkId(skId);
    }

}