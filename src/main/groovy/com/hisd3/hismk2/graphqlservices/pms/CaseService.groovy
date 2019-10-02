package com.hisd3.hismk2.graphqlservices.pms

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.domain.Department
import com.hisd3.hismk2.domain.pms.Case
import com.hisd3.hismk2.domain.pms.NurseNote
import com.hisd3.hismk2.domain.pms.Transfer
import com.hisd3.hismk2.domain.pms.VitalSign
import com.hisd3.hismk2.repository.DepartmentRepository
import com.hisd3.hismk2.repository.pms.*
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

import java.time.Instant

@TypeChecked
@Component
@GraphQLApi
class CaseService {
	
	@Autowired
	private PatientRepository patientRepository
	
	@Autowired
	private CaseRepository caseRepository
	
	@Autowired
	private NurseNoteRepository nurseNoteRepository
	
	@Autowired
	private VitalSignRepository vitalSignRepository
	
	@Autowired
	private TransferRepository transferRepository
	
	@Autowired
	private DepartmentRepository departmentRepository
	
	@Autowired
	GeneratorService generatorService
	
	@Autowired
	ObjectMapper objectMapper
	
	//============== All Queries ====================
	
	@GraphQLQuery(name = "cases", description = "Get all Cases")
	List<Case> findAll() {
		return caseRepository.findAll()
	}
	
	@GraphQLQuery(name = "case", description = "Get Case By Id")
	Case findById(@GraphQLArgument(name = "id") UUID id) {
		return caseRepository.findById(id).get()
	}
	
	@GraphQLQuery(name = "patientActiveCase", description = "Get Patient active Case")
	Case getPatientActiveCase(@GraphQLArgument(name = "patientId") UUID patientId) {
		return caseRepository.getPatientActiveCase(patientId)
	}
	
	@GraphQLQuery(name = "caseNurseNotes", description = "Get all Case NurseNotes")
	List<NurseNote> getNurseNotes(@GraphQLContext Case parentCase) {
		return nurseNoteRepository.getNurseNotesByCase(parentCase.id).sort { it.entryDateTime }
	}
	
	@GraphQLQuery(name = "caseVitalSigns", description = "Get all Case VitalSigns")
	List<VitalSign> getVitalSigns(@GraphQLContext Case parentCase) {
		
		return vitalSignRepository.getVitalSignsByCase(parentCase.id).sort { it.entryDateTime }
	}
	
	@GraphQLQuery(name = "caseTransfers", description = "Get all Case Transfers")
	List<Transfer> getTransfers(@GraphQLContext Case parentCase) {
		
		return transferRepository.getTransfersByCase(parentCase.id).sort { it.entryDateTime }
	}
	
	Boolean hasActiveCase(UUID patientId) {
		def currentCase = caseRepository.getPatientActiveCase(patientId)
		
		if (currentCase)
			return true
		else
			return false
	}
	
	@GraphQLMutation
	Case upsertCase(
			@GraphQLArgument(name = "id") UUID id,
			@GraphQLArgument(name = "fields") Map<String, Object> fields
	) {
		
		if (id) {
			Case caseObj = caseRepository.findById(id).get()
			objectMapper.updateValue(caseObj, fields)
			return caseRepository.save(caseObj)
		} else {
			
			def serviceType = fields["serviceType"] as String
			def registryType = fields["registryType"] as String
			def accommodationType = fields["accommodationType"] as String
			def departmentId = fields["departmentId"] as String
			def patientId = fields["patientId"] as UUID
			
			//Initialize patient data
			def caseObj = objectMapper.convertValue(fields, Case)
			
			Department department = departmentRepository.findById(departmentId as UUID).get()
			
			def caseNo = generatorService?.getNextValue(GeneratorType.CASE_NO, { i ->
				StringUtils.leftPad(i.toString(), 6, "0")
			})
			
			caseObj.patient = patientRepository.findById(patientId).get()
			
			caseObj.department = department
			caseObj.caseNo = caseNo
			caseObj.serviceType = serviceType
			caseObj.registryType = registryType
			caseObj.accommodationType = accommodationType
			caseObj.entryDateTime = Instant.now()
			
			if (hasActiveCase(patientId)) {
				caseObj.status = ""
			}
			
			caseRepository.save(caseObj)
			
			//END.Initialize case data -------
			
			//Initialize transfer data -------
			Transfer pTransfer = new Transfer()
			
			pTransfer.registryType = registryType
			pTransfer.department = department
			pTransfer.entryDateTime = Instant.now()
			pTransfer.parentCase = caseObj
			
			transferRepository.save(pTransfer)
			//END.Initialize transfer data -------
			
			return caseObj
		}
	}
	
	@GraphQLMutation
	Case changeCaseStatus(
			@GraphQLArgument(name = "id") String id,
			@GraphQLArgument(name = "fields") Map<String, Object> fields
	) {
		
		Case caseObj = caseRepository.findById(fields["caseId"] as UUID).get()
		caseObj.status = fields["status"] as String
		return caseRepository.save(caseObj)
	}
}
