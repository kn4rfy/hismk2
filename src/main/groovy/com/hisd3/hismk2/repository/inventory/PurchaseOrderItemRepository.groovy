package com.hisd3.hismk2.repository.inventory

import com.hisd3.hismk2.domain.inventory.PurchaseOrderItems
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PurchaseOrderItemRepository extends JpaRepository<PurchaseOrderItems, UUID> {
	@Query("select pi from PurchaseOrderItems pi where pi.purchaseOrder.id = :poId")
	List<PurchaseOrderItems> findByPurchaseOrderId(@Param("poId") UUID poId)
}