package com.hisd3.hismk2.repository.inventory

import com.hisd3.hismk2.domain.inventory.StockRequest
import org.springframework.data.jpa.repository.JpaRepository

interface StockRequestRepository extends JpaRepository<StockRequest, UUID> {

}