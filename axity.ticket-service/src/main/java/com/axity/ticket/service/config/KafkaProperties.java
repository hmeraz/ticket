package com.axity.ticket.service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "ticket.kafka")
public class KafkaProperties {
    // Getters y Setters
    private String topic;
    private String trustedPackages;

}
