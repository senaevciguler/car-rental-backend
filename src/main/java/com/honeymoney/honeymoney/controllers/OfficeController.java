package com.honeymoney.honeymoney.controllers;

import com.honeymoney.honeymoney.dto.OfficeDto;
import com.honeymoney.honeymoney.models.Office;
import com.honeymoney.honeymoney.repositories.OfficeRepository;
import com.honeymoney.honeymoney.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins ="http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class OfficeController {

    @Autowired
    OfficeRepository officeRepository;


    @GetMapping("/offices")
    public Result getOfficeList() {
        return Result.Success.builder()
                .payload(officeRepository.findAll())
                .build();
    }

    @GetMapping("/offices/{id}")
    public Result getOffice(@PathVariable Long id) {
        return Result.Success.builder()
                .payload(officeRepository.findById(id))
                .build();
    }

    @DeleteMapping("/offices/{id}")
    public Result deleteOffice(@PathVariable Long id) {
        officeRepository.deleteById(id);
        return Result.Success.builder()
                .message("Office deleted successfully")
                .build();
    }

    @PutMapping("/offices/{id}")
    public Result updateCar(@PathVariable long id, @RequestBody Office updateOffice) {

        Office updatedOffice = officeRepository.findById(id).map(office -> {
            office.setName(updateOffice.getName());
            office.setCars(updateOffice.getCars());
            return officeRepository.save(office);
        }).orElseGet(() -> {
            updateOffice.setId(id);
            return officeRepository.save(updateOffice);
        });
        return Result.Success.builder()
                .message("Office updated successfully")
                .payload(updateOffice)
                .build();
    }

    @PostMapping("/offices")
    public Result createOffice(@RequestBody OfficeDto officeDto) {
        Office office = Office.builder()
                .name(officeDto.getName())
                .cars(officeDto.getCars())
                .build();

        return Result.Success.builder()
                .message("Office saved successfully")
                .payload(officeRepository.save(office))
                .build();
    }

}
