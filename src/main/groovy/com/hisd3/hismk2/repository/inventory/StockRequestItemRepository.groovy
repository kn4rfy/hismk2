package com.hisd3.hismk2.repository.inventory

import com.hisd3.hismk2.domain.inventory.StockRequestItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface StockRequestItemRepository extends JpaRepository<StockRequestItem, UUID> {
	
	@Query(value = "Select sri from StockRequestItem sri where sri.stockRequest.id = :srId")
	List<StockRequestItem> getSRItemsBySRId(@Param("srId") UUID srId)
	
}