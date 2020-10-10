package com.honeymoney.honeymoney.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "model")
    private String model;
    @Column(name = "bodyType")
    private String bodyType;
    @Column(name = "year")
    private Long year;
    @Column(name = "color")
    private String color;
    @Column(name = "mileage")
    private Long mileage;
    /*
    @Column(name = "availability")
    private boolean availability;
    */
    @Column(name="checkInDate")
    private Date checkInDate;
    @Column(name="checkOutDate")
    private Date checkOutDate;
    @Column(name="price")
    private Long price;

    @Lob
    @Column(name = "photo")
    private byte[] photo;
}
