package com.axity.ticket.service.mapper;

import com.axity.ticket.commons.dto.TicketDto;
import com.axity.ticket.model.TicketDocument;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    TicketDto toDto(TicketDocument document);

    TicketDocument toDocument(TicketDto dto);

    List<TicketDto> toDtoList(List<TicketDocument> documentList);

    List<TicketDocument> toDocumentList(List<TicketDto> dtoList);
}