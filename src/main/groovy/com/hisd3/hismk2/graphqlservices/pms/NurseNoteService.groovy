package com.hisd3.hismk2.graphqlservices.pms

import com.hisd3.hismk2.repository.pms.NurseNoteRepository
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
class NurseNoteService {
	
	@Autowired
	private NurseNoteRepository nurseNoteRepository
	
	//============== All Queries ====================
//	@GraphQLQuery(name = "caseNurseNotes", description = "Get all nurse notes")
//	List<NurseNote> getNurseNotes() {
//		nurseNoteDao.getNurseNotes()
//	}
}
