package com.hisd3.hismk2.graphqlservices.pms

import com.hisd3.hismk2.domain.pms.Medication
import com.hisd3.hismk2.repository.pms.MedicationRepository
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
class MedicationService {
	
	@Autowired
	private MedicationRepository medicationRepository
	
	//============== All Queries ====================
	
	@GraphQLQuery(name = "medicationsByCase", description = "Get all medications by case ID")
	List<Medication> getMedicationsByCase(@GraphQLArgument(name = "caseId") UUID caseId) {
		return medicationRepository.getMedicationsByCase(caseId)
	}
	
	@GraphQLQuery(name = "medicationsByCaseAndType", description = "Get all medications by case ID and Type")
	List<Medication> getMedicationsByCaseAndType(@GraphQLArgument(name = "caseId") UUID caseId, @GraphQLArgument(name = "type") String type) {
		return medicationRepository.getMedicationsByCaseAndType(caseId, type)
	}
}
