package com.axity.ticket.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {

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
