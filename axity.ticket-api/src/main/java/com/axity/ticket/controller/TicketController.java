package com.axity.ticket.controller;
import com.axity.ticket.commons.aspectj.JsonResponseInterceptor;
import com.axity.ticket.commons.dto.TicketDto;
import com.axity.ticket.facade.TicketFacade;
import com.fasterxml.jackson.core.JsonProcessingException;
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

    @JsonResponseInterceptor
    @PostMapping
    public ResponseEntity<Void> saveTicket(@RequestBody TicketDto ticket) throws JsonProcessingException {
        ticketFacade.publishTicket(ticket);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @JsonResponseInterceptor
    @GetMapping("/{skId}")
    public ResponseEntity<TicketDto> getById(@PathVariable Long skId) throws JsonProcessingException {
        TicketDto ticket = ticketFacade.getbyId(skId);
        if (ticket != null) {
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @JsonResponseInterceptor
    @GetMapping
    public ResponseEntity<List<TicketDto>> getAll() {
        List<TicketDto> tickets = ticketFacade.getAll();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @JsonResponseInterceptor
    @PutMapping("/{skId}")
    public ResponseEntity<Void> update(@PathVariable Long skId, @RequestBody TicketDto ticket) {
        ticketFacade.update(skId, ticket);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @JsonResponseInterceptor
    @DeleteMapping("/{skId}")
    public ResponseEntity<Void> delete(@PathVariable Long skId) {
        ticketFacade.delete(skId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}