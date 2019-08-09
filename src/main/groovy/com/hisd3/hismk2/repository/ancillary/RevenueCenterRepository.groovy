package com.hisd3.hismk2.repository.ancillary

import com.hisd3.hismk2.domain.ancillary.RevenueCenter
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface RevenueCenterRepository extends JpaRepository<RevenueCenter, UUID> {

    @Query(
            value = "Select r from RevenueCenter r",
            countQuery = "Select count(r) from RevenueCenter r"
    )
    List<RevenueCenter> getAllRevenueCenters()
}
