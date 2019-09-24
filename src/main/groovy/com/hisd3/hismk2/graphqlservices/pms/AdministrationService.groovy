package com.hisd3.hismk2.graphqlservices.pms

import com.hisd3.hismk2.domain.pms.Administration
import com.hisd3.hismk2.repository.pms.AdministrationRepository
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
class AdministrationService {
	
	@Autowired
	private AdministrationRepository administrationRepository
	
	//============== All Queries ====================
	
	@GraphQLQuery(name = "medicationAdministrations", description = "Get all Administrations by Medication ID")
	List<Administration> getMedicationAdministrations(@GraphQLArgument(name = "medication") UUID medication) {
		return administrationRepository.getMedicationAdministrations(medication)
	}
	
	@GraphQLQuery(name = "medicationAdministrationsByCase", description = "Get all Administrations by case ID")
	List<Administration> getMedicationAdministrationsByCase(@GraphQLArgument(name = "caseId") UUID caseId) {
		return administrationRepository.getMedicationAdministrationsByCase(caseId)
	}
}
