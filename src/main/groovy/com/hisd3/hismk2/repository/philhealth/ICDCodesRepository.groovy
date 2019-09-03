package com.hisd3.hismk2.repository.philhealth

import com.hisd3.hismk2.domain.bms.ICDCode
import groovy.transform.TypeChecked
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

@TypeChecked
interface ICDCodesRepository extends JpaRepository<ICDCode, UUID> {
	
	@Query(value = "Select i from ICDCode i order by diagnosisCode",
			countQuery = "Select count(i) from ICDCode i order by diagnosisCode"
	)
	List<ICDCode> getICDCodes()
}
