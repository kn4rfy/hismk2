package com.hisd3.hismk2.repository.inventory

import com.hisd3.hismk2.domain.inventory.PurchaseOrderItems
import org.springframework.data.jpa.repository.JpaRepository

interface PurchaseOrderItemRepository extends JpaRepository<PurchaseOrderItems, UUID> {

}