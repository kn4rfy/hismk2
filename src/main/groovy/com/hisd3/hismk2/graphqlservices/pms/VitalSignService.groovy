package com.hisd3.hismk2.graphqlservices.pms

import com.hisd3.hismk2.dao.pms.VitalSignDao
import com.hisd3.hismk2.domain.pms.VitalSign
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
class VitalSignService {
	
	@Autowired
	VitalSignDao vitalSignDao
	
	//============== All Queries ====================
	
	@GraphQLQuery(name = "vitalSigns", description = "Get all vital signs")
	List<VitalSign> findAll() {
		return vitalSignDao.findAll()
	}

	@GraphQLQuery(name = "vitalSignsByCase", description = "Get all vital signs by case ID")
	List<VitalSign> getVitalSignsByCase(@GraphQLArgument(name = "caseId") UUID caseId) {
		return vitalSignDao.getVitalSignsByCase(caseId)
	}
}
