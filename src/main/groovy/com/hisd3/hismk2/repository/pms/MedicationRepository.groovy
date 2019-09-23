package com.hisd3.hismk2.repository.pms

import com.hisd3.hismk2.domain.pms.Medication
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface MedicationRepository extends JpaRepository<Medication, UUID> {
	
	@Query(value = "Select medication from Medication medication where medication.parentCase.id = :parentCase")
	List<Medication> getMedicationsByCase(@Param("parentCase") UUID parentCase)
	
	@Query(value = "Select medication from Medication medication where medication.parentCase.id = :parentCase and medication.type = :type")
	List<Medication> getMedicationsByCaseAndType(@Param("parentCase") UUID parentCase, @Param("type") String type)
	
}