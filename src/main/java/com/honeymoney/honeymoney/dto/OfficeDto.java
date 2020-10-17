package com.honeymoney.honeymoney.dto;

import com.honeymoney.honeymoney.models.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OfficeDto {
    private Long id;
    private String name;
    private List<Car> cars;
}
