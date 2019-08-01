package com.hisd3.hismk2.repository.pms

import com.hisd3.hismk2.domain.pms.VitalSign
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface VitalSignRepository extends JpaRepository<VitalSign, UUID> {
	
	@Query(value = "Select pvs from VitalSign pvs")
	List<VitalSign> getVitalSigns()
	
	@Query(value = "Select pvs from VitalSign pvs where pvs.patientCase = :patientCase")
	List<VitalSign> getVitalSignsByPatientCase(@Param("patientCase") String patientCase)
	
}