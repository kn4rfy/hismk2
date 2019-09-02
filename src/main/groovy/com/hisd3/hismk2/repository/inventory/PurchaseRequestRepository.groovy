package com.hisd3.hismk2.repository.inventory

import com.hisd3.hismk2.domain.inventory.PurchaseRequest
import org.springframework.data.jpa.repository.JpaRepository

interface PurchaseRequestRepository extends JpaRepository<PurchaseRequest, UUID> {

}