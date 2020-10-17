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
public class BookingPostDto implements Serializable {

    private Date checkInDate;
    private Date checkOutDate;
    private Long car;
    private String office;



}
