package com.hisd3.hismk2.repository.ancillary

import com.hisd3.hismk2.domain.ancillary.OrderSlipItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface OrderSlipItemRepository extends JpaRepository<OrderSlipItem, UUID> {
	
	@Query(value = "Select orderSlipItem from OrderSlipItem orderSlipItem where orderSlipItem.orderslip.id =:orderSlipId")
	List<OrderSlipItem> getByOrderSlip(@Param("orderSlipId") UUID orderSlipId)
}
