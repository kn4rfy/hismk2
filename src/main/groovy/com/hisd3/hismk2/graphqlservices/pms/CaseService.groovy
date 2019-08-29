package com.hisd3.hismk2.graphqlservices.pms

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.dao.DepartmentDao
import com.hisd3.hismk2.dao.pms.CaseDao
import com.hisd3.hismk2.dao.pms.PatientDao
import com.hisd3.hismk2.dao.pms.TransferDao
import com.hisd3.hismk2.domain.Department
import com.hisd3.hismk2.domain.pms.Case
import com.hisd3.hismk2.domain.pms.NurseNote
import com.hisd3.hismk2.domain.pms.Transfer
import com.hisd3.hismk2.domain.pms.VitalSign
import com.hisd3.hismk2.services.GeneratorService
import com.hisd3.hismk2.services.GeneratorType
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLContext
import io.leangen.graphql.annotations.GraphQLMutation
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import java.time.LocalDateTime

@TypeChecked
@Component
@GraphQLApi
class CaseService {
	
	@Autowired
	PatientDao patientDao
	
	@Autowired
	CaseDao caseDao
	
	@Autowired
	TransferDao transferDao
	
	@Autowired
	DepartmentDao departmentDao
	
	@Autowired
	GeneratorService generatorService
	
	@Autowired
	ObjectMapper objectMapper
	
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

	@GraphQLMutation
	Case upsertCase(
			@GraphQLArgument(name = "id") String id,
			@GraphQLArgument(name = "fields") Map<String, Object> fields
	) {
		
		if (id) {
			def caseObj = caseDao.findById(id)
			objectMapper.updateValue(caseObj, fields)
			return caseDao.save(caseObj)
		} else {
			
			def serviceType = fields["serviceType"] as String
			def registryType = fields["registryType"] as String
			def accommodationType = fields["accommodationType"] as String
			def departmentId = fields["departmentId"] as String
			def patientId = fields["patientId"] as String
			
			//Initialize patient data
			def caseObj = objectMapper.convertValue(fields, Case)
			
			Department department = departmentDao.findById(departmentId)

			def caseNo = generatorService?.getNextValue(GeneratorType.CASE_NO, { i ->
				StringUtils.leftPad(i.toString(), 6, "0")
			})
			
			caseObj.patient = patientDao.findById(patientId)

			caseObj.department = department
			caseObj.caseNo = caseNo
			caseObj.serviceType = serviceType
			caseObj.registryType = registryType
			caseObj.accommodationType = accommodationType
			caseObj.entryDatetime = LocalDateTime.now()
			
			if (caseDao.hasActiveCase(patientId)) {
				caseObj.status = ""
			}
			
			caseDao.save(caseObj)
			
			//END.Initialize case data -------
			
			//Initialize transfer data -------
			Transfer pTransfer = new Transfer()
			
			pTransfer.registryType = registryType
			pTransfer.department = department
			pTransfer.entryDatetime = LocalDateTime.now()
			pTransfer.parentCase = caseObj
			
			transferDao.save(pTransfer)
			//END.Initialize transfer data -------
			
			return caseObj
		}
	}

	@GraphQLMutation
	Case changeCaseStatus(
			@GraphQLArgument(name = "id") String id,
			@GraphQLArgument(name = "fields") Map<String, Object> fields
	) {

		def caseObj = caseDao.findById(fields["caseId"] as String)
		caseObj.status = fields["status"] as String
		caseDao.save(caseObj)

		return caseObj
	}
}
