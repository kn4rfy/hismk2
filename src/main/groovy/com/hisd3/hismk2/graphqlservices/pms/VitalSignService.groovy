package com.hisd3.hismk2.graphqlservices.pms

import com.hisd3.hismk2.domain.pms.VitalSign
import com.hisd3.hismk2.repository.pms.VitalSignRepository
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
class VitalSignService {
	
	@Autowired
	private VitalSignRepository vitalSignRepository
	
	//============== All Queries ====================
	
	@GraphQLQuery(name = "vitalSigns", description = "Get all VitalSigns")
	List<VitalSign> findAll() {
		return vitalSignRepository.findAll().sort { it.entryDateTime }
	}
	
	@GraphQLQuery(name = "vitalSign", description = "Get VitalSign By Id")
	VitalSign findById(@GraphQLArgument(name = "id") UUID id) {
		return vitalSignRepository.findById(id).get()
	}
	
	@GraphQLQuery(name = "vitalSignsByCase", description = "Get all VitalSigns by Case Id")
	List<VitalSign> getVitalSignsByCase(@GraphQLArgument(name = "caseId") UUID caseId) {
		return vitalSignRepository.getVitalSignsByCase(caseId).sort { it.entryDateTime }
	}
}
