package com.hisd3.hismk2.repository.pms

import com.hisd3.hismk2.domain.pms.PatientCase
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PatientCaseRepository extends JpaRepository<PatientCase, UUID> {
	
	@Query(value = "Select p from PatientCase p")
	List<PatientCase> getPatientCases()
}