package com.hisd3.hismk2.repository.pms

import com.hisd3.hismk2.domain.pms.Case
import groovy.transform.TypeChecked
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

@TypeChecked
interface CaseRepository extends JpaRepository<Case, UUID> {
	
	@Query(
			value = "Select c from Case c where c.patient.id = :patientId and c.status = 'ACTIVE'"
	)
	Case getPatientActiveCase(@Param("patientId") UUID patientId)
	
	@Query(
			value = "Select c from Case c where c.caseNo = :caseNo"
	)
	Case findByCaseNo(@Param("caseNo") String caseNo)
	
	@Query(value = '''Select c from Case c where c.patient.id = :patientId order by c.entryDatetime''')
	List<Case> getPatientCases(@Param("patientId") UUID patientId)
}