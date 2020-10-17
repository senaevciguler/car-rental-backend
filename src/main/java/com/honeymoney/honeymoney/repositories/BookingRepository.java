package com.honeymoney.honeymoney.repositories;

import com.honeymoney.honeymoney.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}

