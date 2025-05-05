package com.axity.ticket.service.consumer;

import com.axity.ticket.commons.dto.TicketDto;
import com.axity.ticket.service.TicketService;
import com.axity.ticket.service.redis.RedisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TicketConsumerService {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private RedisService redisService;

    @KafkaListener(topics = "${ticket.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listener(TicketDto ticket) throws JsonProcessingException {
        System.out.println("Mensaje recibido: " + ticket);
        redisService.save(String.valueOf(ticket.getSkId()), ticket);
        ticketService.save(ticket);
    }
}
