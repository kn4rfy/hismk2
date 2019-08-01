package com.hisd3.hismk2.graphqlservices.pms

import com.hisd3.hismk2.dao.pms.NurseNoteDao
import com.hisd3.hismk2.domain.pms.NurseNote
import com.hisd3.hismk2.domain.pms.PatientCase
import io.leangen.graphql.annotations.GraphQLContext
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
class NurseNoteService {
	
	@Autowired
	NurseNoteDao nurseNoteDao
	
	//============== All Queries ====================
	@GraphQLQuery(name = "nurseNotes", description = "Get all nurse notes")
	List<NurseNote> getNurseNotes() {
		nurseNoteDao.getNurseNotes()
	}
	
	@GraphQLQuery(name = "nurseNotesByPatientCase", description = "Get nurse notes by patient case")
	Set<NurseNote> getNurseNotesByPatientCase(@GraphQLContext PatientCase patientCase) {
		nurseNoteDao.getNurseNotesByPatientCase(patientCase)
	}
}
