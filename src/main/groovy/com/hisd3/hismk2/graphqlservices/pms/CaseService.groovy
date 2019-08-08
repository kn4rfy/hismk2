package com.hisd3.hismk2.graphqlservices.pms

import com.hisd3.hismk2.dao.pms.CaseDao
import com.hisd3.hismk2.domain.pms.Case
import com.hisd3.hismk2.domain.pms.NurseNote
import com.hisd3.hismk2.domain.pms.VitalSign
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLContext
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@TypeChecked
@Component
@GraphQLApi
class CaseService {
	
	@Autowired
	CaseDao caseDao
	
	@PersistenceContext
	EntityManager entityManager
	
	//============== All Queries ====================
	
	@GraphQLQuery(name = "cases", description = "Get all cases")
	List<Case> findAll() {
		return caseDao.findAll()
	}
	
	@GraphQLQuery(name = "case", description = "Get Case By Id")
	Case findById(@GraphQLArgument(name = "id") String id) {
		
		return caseDao.findById(id)
	}
	
	@GraphQLQuery(name = "patientActiveCase", description = "Get Patient active Case")
	Case getPatientActiveCase(@GraphQLArgument(name = "patientId") UUID patientId) {
		return caseDao.getPatientActiveCase(patientId)
	}
	
	@GraphQLQuery(name = "caseNurseNotes", description = "Get all Case NurseNotes")
	Set<NurseNote> getNurseNotes(@GraphQLContext Case parentCase) {
		
		return caseDao.getNurseNotes(parentCase)
	}
	
	@GraphQLQuery(name = "caseVitalSigns", description = "Get all Case VitalSigns")
	Set<VitalSign> getVitalSigns(@GraphQLContext Case parentCase) {
		
		return caseDao.getVitalSigns(parentCase)
	}
}
