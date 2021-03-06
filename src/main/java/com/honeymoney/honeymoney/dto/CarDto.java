package com.honeymoney.honeymoney.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarDto implements Serializable {

    private Long id;
    private String model;
    private String bodyType;
    private Long year;
    private String color;
    private Long mileage;
    /*
    private boolean availability;
    private Date checkInDate;
    private Date checkOutDate;
    */
    private Long price;
    //private MultipartFile photo;
}
