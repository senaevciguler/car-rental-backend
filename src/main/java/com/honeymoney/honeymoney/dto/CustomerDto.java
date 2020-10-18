package com.honeymoney.honeymoney.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDto {

    private Long id;
    private String name;
    private String username;
    private String lastName;
    private String password;
    //private List<Booking> bookings;
}
