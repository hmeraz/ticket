package com.axity.ticket.model;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.data.cassandra.core.mapping.CassandraType.Name.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("ticket")
public class TicketDocument {

    @PrimaryKey
    @Column("sk_id")
    private Long skId;

    @Column("dx_tcn")
    @CassandraType(type = TEXT)
    private String dxTcn;

    @Column("dx_digital")
    @CassandraType(type = INT)
    private Integer dxDigital;

    @Column("dx_email")
    @CassandraType(type = TEXT)
    private String dxEmail;

    @Column("dx_phone")
    @CassandraType(type = BIGINT)
    private Long dxPhone;

    @Column("dx_ticket")
    @CassandraType(type = TEXT)
    private String dxTicket;

    @Column("dx_printed")
    @CassandraType(type = INT)
    private Integer dxPrinted;

    @Column("dx_store_format")
    @CassandraType(type = TEXT)
    private String dxStoreFormat;

    @Column("dx_date")
    @CassandraType(type = DATE)
    private LocalDate dxDate;

    @Column("dx_time")
    @CassandraType(type = TIME)
    private LocalTime dxTime;

    @Column("dx_tyc")
    @CassandraType(type = INT)
    private Integer dxTyc;

    @Column("dx_store_name")
    @CassandraType(type = TEXT)
    private String dxStoreName;

    @Column("dx_tda")
    @CassandraType(type = INT)
    private Integer dxTda;

    @Column("dx_op")
    @CassandraType(type = BIGINT)
    private Long dxOp;

    @Column("dx_te")
    @CassandraType(type = INT)
    private Integer dxTe;

    @Column("dx_tr")
    @CassandraType(type = INT)
    private Integer dxTr;

    @Column("dx_store_number")
    @CassandraType(type = INT)
    private Integer dxStoreNumber;

    @Column("dx_uuid")
    @CassandraType(type = TEXT)
    private String dxUuid;
}
