package com.honeymoney.honeymoney.controllers;

import com.honeymoney.honeymoney.dto.CarDto;
import com.honeymoney.honeymoney.models.Car;
import com.honeymoney.honeymoney.repositories.CarRepository;
import com.honeymoney.honeymoney.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class CarController {


    @Autowired
    CarRepository carRepository;


    @GetMapping("/cars")
    public Result getCarsList() {
        return Result.Success.builder()
                .payload(carRepository.findAll())
                .build();
    }

    @GetMapping("/cars/{id}")
    public Result getCar(@PathVariable Long id) {
        return Result.Success.builder()
                .payload(carRepository.findById(id))
                .build();
    }

    @DeleteMapping("/cars/{id}")
    public Result deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);
        return Result.Success.builder()
                .message("Car deleted successfully")
                .build();
    }

    @PutMapping("/cars/{id}")
    public Result updateCar(@PathVariable long id, @RequestBody Car updateCar) {

        Car updatedCar = carRepository.findById(id).map(car -> {
            car.setColor(updateCar.getColor());
            car.setCheckInDate(updateCar.getCheckInDate());
            car.setCheckOutDate(updateCar.getCheckOutDate());
            car.setOffice(updateCar.getOffice());
            car.setBodyType(updateCar.getBodyType());
            car.setMileage(updateCar.getMileage());
            car.setModel(updateCar.getModel());
            car.setYear(updateCar.getYear());
            return carRepository.save(car);
        }).orElseGet(() -> {
            updateCar.setId(id);
            return carRepository.save(updateCar);
        });
        return Result.Success.builder()
                .message("Car updated successfully")
                .payload(updatedCar)
                .build();
    }

    @PostMapping("/cars")
    public Result createCar(@RequestBody CarDto carDto) {
        Car car = Car.builder()
                .office(carDto.getOffice())
                .checkInDate(carDto.getCheckInDate())
                .checkOutDate(carDto.getCheckOutDate())
                .bodyType(carDto.getBodyType())
                .color(carDto.getColor())
                .mileage(carDto.getMileage())
                .model(carDto.getModel())
                //.photo(carDto.getPhoto()!=null? carDto.getPhoto().getBytes():null)
                .year(carDto.getYear())
                .build();

        return Result.Success.builder()
                .message("Car saved successfully")
                .payload(carRepository.save(car))
                .build();
    }

    @PostMapping("/cars/image/{id}")
    public Result createCar(@PathVariable long id,  @ModelAttribute MultipartFile file) throws IOException {
        Car car = carRepository.getOne(id);
        car.setPhoto(file.getBytes());

        return Result.Success.builder()
                .message("Car saved successfully")
                .payload(carRepository.save(car))
                .build();
    }

}
