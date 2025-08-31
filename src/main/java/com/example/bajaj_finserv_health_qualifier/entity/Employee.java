package com.example.bajaj_finserv_health_qualifier.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "EMPLOYEE")
@Data
public class Employee {
    @Id
    @Column(name = "EMP_ID")
    private Integer empId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "DOB")
    private LocalDate dob;

    @Column(name = "GENDER")
    private String gender;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT")
    private Department department;
}
