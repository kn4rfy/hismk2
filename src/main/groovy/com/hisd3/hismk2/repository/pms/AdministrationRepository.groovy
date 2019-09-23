package com.hisd3.hismk2.repository.pms

import com.hisd3.hismk2.domain.pms.Administration
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface AdministrationRepository extends JpaRepository<Administration, UUID> {
	
	@Query(value = "Select administration from Administration administration where administration.medication.id = :medication")
	List<Administration> getMedicationAdministrations(@Param("medication") UUID medication)
	
}