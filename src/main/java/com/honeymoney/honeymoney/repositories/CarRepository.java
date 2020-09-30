package com.honeymoney.honeymoney.repositories;

import com.honeymoney.honeymoney.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
