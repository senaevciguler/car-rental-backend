package com.honeymoney.honeymoney.controllers;

import com.honeymoney.honeymoney.dto.CarDto;
import com.honeymoney.honeymoney.models.Booking;
import com.honeymoney.honeymoney.models.Car;
import com.honeymoney.honeymoney.repositories.BookingRepository;
import com.honeymoney.honeymoney.repositories.CarRepository;
import com.honeymoney.honeymoney.repositories.OfficeRepository;
import com.honeymoney.honeymoney.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class CarController {


    @Autowired
    CarRepository carRepository;

    @Autowired
    OfficeRepository officeRepository;

    @Autowired
    BookingRepository bookingRepository;


    @GetMapping("/cars")
    public Result getCarsList() {
        return Result.Success.builder()
                .payload(carRepository.findAll())
                .build();
    }


    @GetMapping("/cars/available")
    public Result getCarsList(@RequestParam String office,
                              @RequestParam("checkInDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkInDate,
                              @RequestParam("checkOutDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkOutDate) {

        List<Car> officesCar = (List<Car>) officeRepository.findByOfficeName(office).getCars();
        List<Car> bookedCar = bookingRepository.findAll().stream()
                .filter(x -> office.equals(x.getOffice().getName()) &&
                        x.getCheckInDate().before(checkOutDate) &&
                        x.getCheckOutDate().after(checkInDate)
                ).map(Booking::getCar).collect(Collectors.toList());

        officesCar.removeAll(bookedCar);
        return Result.Success.builder()
                .payload(officesCar)
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
            //car.setCheckInDate(updateCar.getCheckInDate());
            //car.setCheckOutDate(updateCar.getCheckOutDate());
            car.setBodyType(updateCar.getBodyType());
            car.setMileage(updateCar.getMileage());
            car.setModel(updateCar.getModel());
            car.setYear(updateCar.getYear());
            car.setPrice(updateCar.getPrice());
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
               // .checkInDate(carDto.getCheckInDate())
                //.checkOutDate(carDto.getCheckOutDate())
                .bodyType(carDto.getBodyType())
                .color(carDto.getColor())
                .mileage(carDto.getMileage())
                .model(carDto.getModel())
                .price(carDto.getPrice())
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
