package com.hisd3.hismk2.graphqlservices.pms

import com.hisd3.hismk2.dao.pms.IntakeDao
import com.hisd3.hismk2.dao.pms.NurseNoteDao
import com.hisd3.hismk2.domain.pms.Intake
import com.hisd3.hismk2.domain.pms.NurseNote
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
class IntakeService {
	
	@Autowired
	IntakeDao intakeDao
	
	//============== All Queries ====================
	
	@GraphQLQuery(name = "intakes", description = "Get all intakes")
	List<Intake> findAll() {
		return intakeDao.findAll()
	}

	@GraphQLQuery(name = "intakesByCase", description = "Get all patient intakes by case ID")
	List<Intake> getIntakesByCase(@GraphQLArgument(name = "caseId") UUID caseId) {
		return intakeDao.getIntakesByCase(caseId)
	}
}
