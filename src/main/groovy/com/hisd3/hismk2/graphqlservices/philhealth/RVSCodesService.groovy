package com.hisd3.hismk2.graphqlservices.philhealth

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.dao.philhealth.RVSDao
import com.hisd3.hismk2.domain.philhealth.RVSCode
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@TypeChecked
@Component
@GraphQLApi
class RVSCodesService {
	
	@Autowired
	RVSDao rvsDao
	
	@PersistenceContext
	EntityManager entityManager
	
	@Autowired
	ObjectMapper objectMapper
	
	//============== All Queries ====================
	
	@GraphQLQuery(name = "rvs_codes", description = "Get All RVS Codes")
	List<RVSCode> getRVSCodes() {
		return rvsDao.getRVSCodes()
	}
	
	@GraphQLQuery(name = "searchRVSCodes", description = "search RVS Codes")
	List<RVSCode> searchRVSCodes(@GraphQLArgument(name = "filter") String filter) {
		return rvsDao.searchRVSCodes(filter)
	}
}
