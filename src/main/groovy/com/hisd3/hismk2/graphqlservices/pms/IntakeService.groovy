package com.hisd3.hismk2.graphqlservices.pms

import com.hisd3.hismk2.domain.pms.Intake
import com.hisd3.hismk2.repository.pms.IntakeRepository
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
class IntakeService {
	
	@Autowired
	private IntakeRepository intakeRepository
	
	//============== All Queries ====================
	
	@GraphQLQuery(name = "intakes", description = "Get all intakes")
	List<Intake> findAll() {
		return intakeRepository.findAll().sort { it.entryDateTime }
	}
	
	@GraphQLQuery(name = "intake", description = "Get Intake By Id")
	Intake findById(@GraphQLArgument(name = "id") UUID id) {
		return intakeRepository.findById(id).get()
	}
	
	@GraphQLQuery(name = "intakesByCase", description = "Get all Intakes by Case Id")
	List<Intake> getIntakesByCase(@GraphQLArgument(name = "caseId") UUID caseId) {
		return intakeRepository.getIntakesByCase(caseId).sort { it.entryDateTime }
	}
}
