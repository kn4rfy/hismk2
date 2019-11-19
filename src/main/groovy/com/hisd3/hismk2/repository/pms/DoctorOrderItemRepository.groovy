package com.hisd3.hismk2.repository.pms

import com.hisd3.hismk2.domain.pms.DoctorOrderItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface DoctorOrderItemRepository extends JpaRepository<DoctorOrderItem, UUID> {
	
	@Query(value = "Select doctorOrderItem from DoctorOrderItem doctorOrderItem where doctorOrderItem.doctorOrder.id = :doctorOrder")
	List<DoctorOrderItem> getDoctorOrderItemsByDoctorOrder(@Param("doctorOrder") UUID doctorOrder)
}