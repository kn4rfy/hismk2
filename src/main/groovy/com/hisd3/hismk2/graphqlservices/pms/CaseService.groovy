package com.hisd3.hismk2.graphqlservices.pms

import com.hisd3.hismk2.dao.pms.CaseDao
import com.hisd3.hismk2.domain.pms.Case
import com.hisd3.hismk2.domain.pms.NurseNote
import com.hisd3.hismk2.domain.pms.VitalSign
import io.leangen.graphql.annotations.GraphQLContext
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
@GraphQLApi
class CaseService {
	
	@Autowired
	CaseDao caseDao
	
	@PersistenceContext
	EntityManager entityManager
	
	//============== All Queries ====================
	@GraphQLQuery(name = "cases", description = "Get all patient vital signs")
	List<Case> getAllCases() {
		return caseDao.findAll()
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
