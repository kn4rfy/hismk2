package com.hisd3.hismk2.graphqlservices.philhealth

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.dao.philhealth.ICDDao
import com.hisd3.hismk2.domain.bms.ICDCode
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@TypeChecked
@Component
@GraphQLApi
class ICDCodesService {

	@Autowired
	ICDDao icdDao

	@PersistenceContext
	EntityManager entityManager

	@Autowired
	ObjectMapper objectMapper

	//============== All Queries ====================

	@GraphQLQuery(name = "icd_codes", description = "Get top 100 ICD Codes")
	List<ICDCode> getICDCodes() {
		return icdDao.getICDCodes()
	}
}
