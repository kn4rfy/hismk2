package com.hisd3.hismk2.graphqlservices.pms

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.dao.DepartmentDao
import com.hisd3.hismk2.dao.pms.PatientDao
import com.hisd3.hismk2.domain.Department
import com.hisd3.hismk2.domain.pms.Case
import com.hisd3.hismk2.domain.pms.Patient
import com.hisd3.hismk2.domain.pms.Transfer
import com.hisd3.hismk2.repository.pms.CaseRepository
import com.hisd3.hismk2.repository.pms.TransferRepository
import com.hisd3.hismk2.services.GeneratorService
import com.hisd3.hismk2.services.GeneratorType
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLContext
import io.leangen.graphql.annotations.GraphQLMutation
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.execution.relay.Page
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.time.LocalDateTime

@TypeChecked
@Component
@GraphQLApi
class PatientService {
	
	@Autowired
	PatientDao patientDao
	
	@Autowired
	private CaseRepository caseRepository
	
	@Autowired
	private TransferRepository transferRepository
	
	@Autowired
	DepartmentDao departmentDao
	
	@Autowired
	GeneratorService generatorService
	
	@Autowired
	ObjectMapper objectMapper
	
	//============== All Queries ====================
	
	@GraphQLQuery(name = "patients", description = "Get All Patients")
	List<Patient> findAll() {
		patientDao.findAll()
	}
	
	@GraphQLQuery(name = "patient", description = "Get Patient By Id")
	Patient findById(@GraphQLArgument(name = "id") UUID id) {
		
		return id ? patientDao.findById(id) : null
	}
	
	@GraphQLQuery(name = "patientsByPage", description = "Get All Patients By Page")
	Page<Patient> getAllPatientsByPage(
			@GraphQLArgument(name = "first") int first,
			@GraphQLArgument(name = "after") String after = "0"
	) {
		
		patientDao.getPatientRelayPage(first, Integer.parseInt(after))
	}
	
	@GraphQLQuery(name = "searchPatients", description = "Search patients")
	List<Patient> searchPatients(@GraphQLArgument(name = "filter") String filter) {
		patientDao.searchPatients(filter)
	}
	
	@GraphQLQuery(name = "patientCases", description = "Get All Patient Cases")
	List<Case> getCases(@GraphQLContext Patient patient) {
		
		return patientDao.getPatientCases(patient)
	}
	
	@GraphQLQuery(name = "patientActiveCase", description = "Get Patient active Case")
	Case getPatientActiveCase(@GraphQLContext Patient patient) {
		return caseRepository.getPatientActiveCase(patient.id)
	}
	
	//============== All Mutations ====================
	
	@GraphQLMutation
	Patient upsertPatient(
			@GraphQLArgument(name = "id") UUID id,
			@GraphQLArgument(name = "fields") Map<String, Object> fields
	) {
		
		if (id) {
			def patient = patientDao.findById(id)
			objectMapper.updateValue(patient, fields)
			return patientDao.save(patient)
		} else {
			
			def serviceType = fields["serviceType"] as String
			def registryType = fields["registryType"] as String
			def accommodationType = fields["accommodationType"] as String
			def departmentId = fields["departmentId"] as String
			
			//Initialize patient data
			def patientObj = objectMapper.convertValue(fields, Patient)
			
			Department department = departmentDao.findById(departmentId as String)
			
			patientObj.patientNo = generatorService.getNextValue(GeneratorType.PATIENT_NO) { Long no ->
				StringUtils.leftPad(no.toString(), 5, "0")
			}
			def patient = patientDao.save(patientObj)
			
			//Initialize case data
			Case pCase = new Case()
			
			def caseNo = generatorService?.getNextValue(GeneratorType.CASE_NO, { i ->
				StringUtils.leftPad(i.toString(), 6, "0")
			})
			
			pCase.patient = patient
			pCase.caseNo = caseNo
			pCase.serviceType = serviceType
			pCase.registryType = registryType
			pCase.accommodationType = accommodationType
			pCase.entryDateTime = LocalDateTime.now()
			pCase.department = department
			
			caseRepository.save(pCase)
			//END.Initialize case data -------
			
			//Initialize transfer data -------
			Transfer pTransfer = new Transfer()
			
			pTransfer.registryType = registryType
			pTransfer.department = department
			pTransfer.entryDateTime = LocalDateTime.now()
			pTransfer.parentCase = pCase
			
			transferRepository.save(pTransfer)
			//END.Initialize transfer data -------
			
			return patient
		}
	}
}
