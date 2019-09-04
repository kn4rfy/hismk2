package com.hisd3.hismk2.repository.philhealth

import com.hisd3.hismk2.domain.bms.ICDCode
import com.hisd3.hismk2.domain.pms.Patient
import groovy.transform.TypeChecked
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

@TypeChecked
interface ICDCodesRepository extends JpaRepository<ICDCode, UUID> {
	
	@Query(value = "Select i from ICDCode i order by diagnosisCode",
			countQuery = "Select count(i) from ICDCode i order by diagnosisCode"
	)
	List<ICDCode> getICDCodes()

	@Query(value = '''Select i from ICDCode i where 
            lower(i.diagnosisCode) like concat('%',:filter,'%') or 
            lower(i.longName) like concat('%',:filter,'%')''')
	List<ICDCode> searchICDCodes(@Param("filter") String filter)
}
