package com.honeymoney.honeymoney.repositories;

import com.honeymoney.honeymoney.models.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


public interface OfficeRepository extends JpaRepository<Office,Long> {

    @Query("SELECT t FROM Office t WHERE t.name = ?1")
    Office findByOfficeName(String officeName);
}
