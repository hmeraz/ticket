package com.axity.ticket.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "ticket")
public class TicketDocument {

    @Id
    private Long skId;
    private String dxTcn;
    private Integer dxDigital;
    private String dxEmail;
    private Long dxPhone;
    private String dxTicket;
    private Integer dxPrinted;
    private String dxStoreFormat;
    private LocalDate dxDate;
    private LocalTime dxTime;
    private Integer dxTyc;
    private String dxStoreName;
    private Integer dxTda;
    private Long dxOp;
    private Integer dxTe;
    private Integer dxTr;
    private Integer dxStoreNumber;
    private String dxUuid;

}
