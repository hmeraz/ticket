package com.axity.ticket.service.util;

import org.springframework.kafka.support.serializer.JsonSerializer;

import com.axity.ticket.commons.request.MessageDto;

/**
 * Message Serializer class
 * 
 * @author username@axity.com
 */
public class MessageSerializer extends JsonSerializer<MessageDto>
{

}
