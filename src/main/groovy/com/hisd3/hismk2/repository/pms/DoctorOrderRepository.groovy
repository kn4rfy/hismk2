package com.hisd3.hismk2.repository.pms

import com.hisd3.hismk2.domain.pms.DoctorOrder
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface DoctorOrderRepository extends JpaRepository<DoctorOrder, UUID> {
	
	@Query(value = "Select doctorOrder from DoctorOrder doctorOrder where doctorOrder.parentCase.id = :parentCase")
	List<DoctorOrder> getDoctorOrdersByCase(@Param("parentCase") UUID parentCase)
}