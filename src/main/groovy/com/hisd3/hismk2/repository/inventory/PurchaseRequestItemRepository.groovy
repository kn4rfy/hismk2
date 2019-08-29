package com.hisd3.hismk2.repository.inventory

import com.hisd3.hismk2.domain.inventory.PurchaseRequestItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PurchaseRequestItemRepository extends JpaRepository<PurchaseRequestItem, UUID> {
	
	@Query(value = "select p from PurchaseRequestItem p where p.refPr = :refPr")
	List<PurchaseRequestItem> getByPrId(@Param("refPr") UUID refPr)
}