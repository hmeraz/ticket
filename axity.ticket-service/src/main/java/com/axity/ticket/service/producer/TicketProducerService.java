package com.axity.ticket.service.producer;

import com.axity.ticket.commons.dto.TicketDto;
import com.axity.ticket.service.config.KafkaProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TicketProducerService {

    private final KafkaTemplate<String, TicketDto> kafkaTemplate;
    private final KafkaProperties kafkaProperties;

    public TicketProducerService(KafkaTemplate<String, TicketDto> kafkaTemplate,
                                 KafkaProperties kafkaProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaProperties = kafkaProperties;
    }

    public void send(TicketDto ticket) {
        kafkaTemplate.send(kafkaProperties.getTopic(), ticket);
    }
}