package com.hisd3.hismk2.repository.inventory


import com.hisd3.hismk2.domain.inventory.StockRequest
import com.hisd3.hismk2.domain.inventory.StockRequestItem
import org.springframework.data.jpa.repository.JpaRepository

interface StockRequestItemRepository extends JpaRepository<StockRequestItem, UUID> {

}