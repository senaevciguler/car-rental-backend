package com.honeymoney.honeymoney.dto;

import com.honeymoney.honeymoney.models.Car;
import com.honeymoney.honeymoney.models.Customer;
import com.honeymoney.honeymoney.models.Office;
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
public class BookingDto implements Serializable {

    private Date checkInDate;
    private Date checkOutDate;
    private Car car;
    private Office office;




}
