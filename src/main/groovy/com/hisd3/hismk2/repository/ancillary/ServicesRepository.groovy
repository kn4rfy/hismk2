package com.hisd3.hismk2.repository.ancillary


import com.hisd3.hismk2.domain.ancillary.Services
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ServicesRepository extends JpaRepository<Services, UUID> {

    @Query(
            value = "Select s from Services s",
            countQuery = "Select count(s) from Services s"
    )
    List<Services> getAllServices()
}