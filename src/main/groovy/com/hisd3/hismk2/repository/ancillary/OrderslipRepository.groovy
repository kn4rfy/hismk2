package com.hisd3.hismk2.repository.ancillary

import com.hisd3.hismk2.domain.ancillary.Orderslip
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface OrderslipRepository extends JpaRepository<Orderslip, UUID> {
	
	@Query(
			value = "Select o from Orderslip o where o.parentCase.id =:id"
	)
	List<Orderslip> findByCase(@Param("id") UUID id)
	
}