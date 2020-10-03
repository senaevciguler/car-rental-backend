package com.honeymoney.honeymoney.services;

import com.honeymoney.honeymoney.dto.CarDto;
import com.honeymoney.honeymoney.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import searchs.CarSearchForm;

import java.util.Optional;

public interface CarService {
    CarDto newCar(CarDto carDto);
    CarDto updateCompany(CarDto carDto);
    Page<CarDto> findCompany(CarSearchForm carSearchForm, Pageable pageable);
    Optional<CarDto> findCompanyById(long id);
    Result deleteCompanyById(long id);
}
