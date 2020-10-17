package com.honeymoney.honeymoney.controllers;


import com.honeymoney.honeymoney.dto.BookingPostDto;
import com.honeymoney.honeymoney.models.Booking;
import com.honeymoney.honeymoney.repositories.BookingRepository;
import com.honeymoney.honeymoney.repositories.CarRepository;
import com.honeymoney.honeymoney.repositories.CustomerRepository;
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


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    OfficeRepository officeRepository;

    @Autowired
    CustomerRepository customerRepository;


    @GetMapping("/booking")
    public Result getBookingList() {
        return Result.Success.builder()
                .payload(bookingRepository.findAll())
                .build();
    }

    @GetMapping("/booking/{id}")
    public Result getBooking(@PathVariable Long id) {
        return Result.Success.builder()
                .payload(bookingRepository.findById(id))
                .build();
    }

    @DeleteMapping("/booking/{id}")
    public Result deleteBooking(@PathVariable Long id) {
        bookingRepository.deleteById(id);
        return Result.Success.builder()
                .message("Book deleted successfully")
                .build();
    }

    @PutMapping("/booking/{id}")
    public Result updateBooking(@PathVariable long id, @RequestBody Booking updateBooking) {

        Booking updatedBook = bookingRepository.findById(id).map(book -> {
            book.setCheckInDate(updateBooking.getCheckInDate());
            book.setCheckOutDate(updateBooking.getCheckOutDate());
            book.setCar(updateBooking.getCar());
            book.setOffice(updateBooking.getOffice());
            return bookingRepository.save(book);
        }).orElseGet(() -> {
            updateBooking.setId(id);
            return bookingRepository.save(updateBooking);
        });
        return Result.Success.builder()
                .message("Book updated successfully")
                .payload(updatedBook)
                .build();
    }

    @PostMapping("/booking")
    public Result createBooking(@RequestBody BookingPostDto bookingDto) {
        Booking booking = Booking.builder()
                .checkInDate(bookingDto.getCheckInDate())
                .checkOutDate(bookingDto.getCheckOutDate())
                .car(carRepository.getOne(bookingDto.getCar()))
                .office(officeRepository.findByOfficeName(bookingDto.getOffice()))
                //.customer(customerRepository.getOne(bookingDto.ge))
                .build();

        booking = bookingRepository.save(booking);
        return Result.Success.builder()
                .message("Car saved successfully")
                .payload(booking)
                .build();
    }


}
