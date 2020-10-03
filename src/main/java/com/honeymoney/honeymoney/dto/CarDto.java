package com.honeymoney.honeymoney.dto;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.honeymoney.honeymoney.models.Office;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
    private int year;
    private String color;
    private int mileage;
    private boolean availability;
    private Office office;
    private Date checkInDate;
    private Date checkOutDate;
    //private MultipartFile photo;
}
