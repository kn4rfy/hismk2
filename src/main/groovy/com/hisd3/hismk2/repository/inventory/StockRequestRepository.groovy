package com.hisd3.hismk2.repository.inventory

import com.hisd3.hismk2.domain.inventory.StockRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface StockRequestRepository extends JpaRepository<StockRequest, UUID> {
	@Query(value = "Select msr from StockRequest msr where msr.status=:status and upper(msr.patient) like  upper(concat('%',:filter,'%'))")
	List<StockRequest> stockRequestByFilter(@Param("status") String status, @Param("filter") String filter)
}