package com.honeymoney.honeymoney.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="checkInDate")
    private Date checkInDate;
    @Column(name="checkOutDate")
    private Date checkOutDate;

    @JsonIgnore
    @ManyToOne
    private Car car;

    @JsonIgnore
    @ManyToOne
    private Office office;

    @ManyToOne
    private Customer customer;
}
