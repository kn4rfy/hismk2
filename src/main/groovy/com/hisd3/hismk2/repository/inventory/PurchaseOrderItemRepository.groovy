package com.hisd3.hismk2.repository.inventory

import com.hisd3.hismk2.domain.inventory.PurchaseOrderItems
import com.hisd3.hismk2.domain.inventory.PurchaseRequestItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


interface PurchaseOrderItemRepository extends JpaRepository<PurchaseOrderItems, UUID> {

}