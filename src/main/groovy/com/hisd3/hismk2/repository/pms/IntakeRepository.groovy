package com.hisd3.hismk2.repository.pms

import com.hisd3.hismk2.domain.pms.Intake
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface IntakeRepository extends JpaRepository<Intake, UUID> {
	
	@Query(value = "Select i from Intake i where i.parentCase.id = :parentCase")
	List<Intake> getIntakesByCase(@Param("parentCase") UUID parentCase)
}