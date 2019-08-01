package com.hisd3.hismk2.graphqlservices.pms

import com.hisd3.hismk2.dao.pms.PatientCaseDao
import com.hisd3.hismk2.domain.pms.NurseNote
import com.hisd3.hismk2.domain.pms.PatientCase
import com.hisd3.hismk2.domain.pms.VitalSign
import io.leangen.graphql.annotations.GraphQLContext
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
class PatientCaseService {
	
	@Autowired
	PatientCaseDao patientCaseDao
	
	//============== All Queries ====================
	@GraphQLQuery(name = "patientCases", description = "Get all patient vital signs")
	List<PatientCase> getPatientCases() {
		patientCaseDao.getPatientCases()
	}
	
	@GraphQLQuery(name = "nurseNotesByPatientCase", description = "Get nurse notes by patient case")
	Set<NurseNote> getNurseNotesByPatientCase(@GraphQLContext PatientCase patientCase) {
		patientCaseDao.getNurseNotesByPatientCase(patientCase)
	}
	
	@GraphQLQuery(name = "vitalSignsByPatientCase", description = "Get patient vital signs by patient case")
	Set<VitalSign> getVitalSignsByPatientCase(@GraphQLContext PatientCase patientCase) {
		patientCaseDao.getVitalSignsByPatientCase(patientCase)
	}
}
