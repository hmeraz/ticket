package com.axity.ticket.controller;
import com.axity.ticket.commons.dto.TicketDto;
import com.axity.ticket.facade.TicketFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin
@Slf4j
public class TicketController {

    @Autowired
    private TicketFacade ticketFacade;

    @PostMapping
    public ResponseEntity<TicketDto> crearTicket(@RequestBody TicketDto ticket) {
        TicketDto creado = ticketFacade.crearTicket(ticket);
        if (creado != null) {
            return new ResponseEntity<>(creado, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{skId}")
    public ResponseEntity<TicketDto> obtenerTicket(@PathVariable Long skId) {
        TicketDto ticket = ticketFacade.obtenerTicket(skId);
        if (ticket != null) {
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<TicketDto>> obtenerTodosLosTickets() {
        List<TicketDto> tickets = ticketFacade.obtenerTodosLosTickets();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @PutMapping("/{skId}")
    public ResponseEntity<Void> actualizarTicket(@PathVariable Long skId, @RequestBody TicketDto ticket) {
        if (ticketFacade.actualizarTicket(skId, ticket)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{skId}")
    public ResponseEntity<Void> eliminarTicket(@PathVariable Long skId) {
        if (ticketFacade.eliminarTicket(skId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}