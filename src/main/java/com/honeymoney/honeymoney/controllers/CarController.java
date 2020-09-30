package com.honeymoney.honeymoney.controllers;

import com.honeymoney.honeymoney.models.Car;
import com.honeymoney.honeymoney.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class CarController {


    @Autowired
    CarRepository carRepository;


    @GetMapping("/cars")
    public List<Car> getCarsList(){
        return carRepository.findAll();
    }

    @GetMapping("/cars/{id}")
    public Car getCar(@PathVariable Long id){
        return carRepository.findById(id).get();
    }

    @DeleteMapping("/cars/{id}")
    public void deleteCar(@PathVariable Long id){
        carRepository.deleteById(id);

    }

    @PutMapping("/cars/{id}")
    public Car updateCar(@PathVariable long id, @RequestBody Car updateCar){

          return carRepository.findById(id).map(car -> {
                car.setColor(updateCar.getColor());
                car.setAvailability(true);
                car.setBodyType(updateCar.getBodyType());
                car.setMileage(updateCar.getMileage());
                car.setModel(updateCar.getModel());
                car.setYear(updateCar.getYear());
                return carRepository.save(car);
            }).orElseGet(() -> {
              updateCar.setId(id);
                return carRepository.save(updateCar);
            });


    }

    @PostMapping("/cars")
    public void createCar(@RequestBody Car car){
        carRepository.save(car);

    }

}
