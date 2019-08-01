package com.hisd3.hismk2.repository.patientRepo

import com.hisd3.hismk2.domain.patientDom.Vitalsign
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface VitalsignRepository extends JpaRepository<Vitalsign, UUID> {
	
	@Query(value = "Select pvs from Vitalsign pvs")
	List<Vitalsign> getVitalsigns()
	
	@Query(value = "Select pvs from Vitalsign pvs where pvs.patientCase = :patientCase")
	List<Vitalsign> getVitalsignsByCase(@Param("patientCase") String patientCase)
	
}