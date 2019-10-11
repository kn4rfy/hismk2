package com.hisd3.hismk2.repository.ancillary

import com.hisd3.hismk2.domain.ancillary.OrderSlipItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface OrderSlipItemRepository extends JpaRepository<OrderSlipItem, UUID> {

	@Query(
			value = "Select oSlipItem from OrderSlipItem oSlipItem where oSlipItem.orderslip.parentCase.id =:id"
	)

	List<OrderSlipItem> findByCase(@Param("id") UUID id)

	@Query(
			value = "Select o from OrderSlipItem o where o.orderslip.parentCase.id =:id and o.service.department.id =:departmentId"
	)
	List<OrderSlipItem> findByCaseAndDepartment(@Param("id") UUID id, @Param("departmentId") UUID departmentId)

	@Query(
			value = "Select o from OrderSlipItem o where o.orderslip.orderslipNo =:orderno and o.service.department.parentDepartment.id =:parentDept"
	)

	List<OrderSlipItem>findByOrderNoandParentDept(@Param("orderno")String orderno, @Param("parentDept")UUID parentDept)

	@Query(
			value = "Select o from OrderSlipItem o where o.orderslip.orderslipNo =:orderno and o.orderslip.parentCase.id =:caseId"
	)
	List<OrderSlipItem>findByOrderNoAndCase(@Param("orderno")String orderno,@Param("caseId")UUID caseId)

}
