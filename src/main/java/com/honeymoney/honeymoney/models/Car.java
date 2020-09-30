package com.honeymoney.honeymoney.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
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
    private int year;
    @Column(name = "color")
    private String color;
    @Column(name = "mileage")
    private int mileage;
    @Column(name = "availability")
    private boolean availability;
}
