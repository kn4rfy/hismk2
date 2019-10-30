package com.hisd3.hismk2.graphqlservices.ancillary

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.dao.ancillary.DiagnosticResultDao
import com.hisd3.hismk2.domain.ancillary.DiagnosticResult
import com.hisd3.hismk2.services.GeneratorService
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@TypeChecked
@Component
@GraphQLApi
class DiagnosticResultService {
	
	@Autowired
	DiagnosticResultDao diagnosticResultDao
	
	@Autowired
	GeneratorService generatorService
	
	@Autowired
	ObjectMapper objectMapper
	
	//============== All Queries ====================
	
	@GraphQLQuery(name = "DiagnosticResults", description = "Get All Diagnostics Results")
	List<DiagnosticResult> findAll() {
		diagnosticResultDao.findAll()
	}
	
	@GraphQLQuery(name = "ResultsByOrderSlip", description = "Get Results by OrderSilp")
	List<DiagnosticResult> findByOrderSlipItem(@GraphQLArgument(name = "id") String id = "") {
		diagnosticResultDao.findByOrderSlipItem(UUID.fromString(id))
	}
	
}
