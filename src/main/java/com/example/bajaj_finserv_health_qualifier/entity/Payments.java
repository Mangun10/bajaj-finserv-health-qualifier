package com.example.bajaj_finserv_health_qualifier.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PAYMENTS")
@Data
public class Payments {
    @Id
    @Column(name = "PAYMENT_ID")
    private Integer paymentId;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "EMP_ID")
    private Employee employee;

    @Column(name = "PAYMENT_TIME")
    private LocalDateTime paymentTime;
}