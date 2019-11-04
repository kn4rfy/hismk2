package com.hisd3.hismk2.repository.ancillary

import com.hisd3.hismk2.domain.ancillary.Orderslip
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface OrderslipRepository extends JpaRepository<Orderslip, UUID> {
	
	@Query(value = "Select o from Orderslip o where lower(o.parentCase.patient.fullName) like lower(concat('%',:filter,'%')) and o.parentCase.registryType =:patientType")
	List<Orderslip> filterByPatientType(@Param("patientType") String patientType, @Param("filter") String filter)
	
	@Query(value = "Select orderSlip from Orderslip orderSlip where orderSlip.parentCase.id = :parentCase order by orderSlip.orderSlipNo desc")
	List<Orderslip> getOrderslipsByCase(@Param("parentCase") UUID parentCase)
	
	@Query(value = "Select orderSlip from Orderslip orderSlip where orderSlip.orderSlipNo = :orderSlipNo order by orderSlip.orderSlipNo desc")
	List<Orderslip> getByOrderSlipNo(@Param("orderSlipNo") String orderSlipNo)
	
	@Query(value = "Select orderSlip from Orderslip orderSlip where orderSlip.department.id = :departmentId order by orderSlip.orderSlipNo desc")
	List<Orderslip> getByOrderSlipDepartment(@Param("departmentId") UUID departmentId)
}
