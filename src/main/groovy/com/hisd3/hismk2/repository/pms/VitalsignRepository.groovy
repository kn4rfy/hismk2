package com.hisd3.hismk2.repository.pms

import com.hisd3.hismk2.domain.pms.Vitalsign
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface VitalsignRepository extends JpaRepository<Vitalsign, UUID> {
	
	@Query(value = "Select pvs from Vitalsign pvs")
	List<Vitalsign> getVitalsigns()
	
	@Query(value = "Select pvs from Vitalsign pvs where pvs.patientCase = :patientCase")
	List<Vitalsign> getVitalsignsByPatientCase(@Param("patientCase") String patientCase)
	
}