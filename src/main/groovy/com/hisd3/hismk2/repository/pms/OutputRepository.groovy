package com.hisd3.hismk2.repository.pms

import com.hisd3.hismk2.domain.pms.Output
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface OutputRepository extends JpaRepository<Output, UUID> {
	
	@Query(value = "Select o from Output o where o.parentCase.id = :parentCase")
	List<Output> getOutputsByCase(@Param("parentCase") UUID parentCase)
}