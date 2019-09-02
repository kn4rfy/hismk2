package com.hisd3.hismk2.graphqlservices.pms

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.dao.DepartmentDao
import com.hisd3.hismk2.dao.bms.RoomDao
import com.hisd3.hismk2.dao.pms.CaseDao
import com.hisd3.hismk2.dao.pms.TransferDao
import com.hisd3.hismk2.domain.Department
import com.hisd3.hismk2.domain.bms.Room
import com.hisd3.hismk2.domain.pms.Case
import com.hisd3.hismk2.domain.pms.Transfer
import com.hisd3.hismk2.services.GeneratorService
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLMutation
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.time.LocalDateTime

@Component
@GraphQLApi
class TransferService {
	
	@Autowired
	TransferDao transferDao
	
	@Autowired
	CaseDao caseDao
	
	@Autowired
	DepartmentDao departmentDao
	
	@Autowired
	RoomDao roomDao
	
	@Autowired
	GeneratorService generatorService
	
	@Autowired
	ObjectMapper objectMapper
	
	//============== All Queries ====================
	
	@GraphQLQuery(name = "transfers", description = "Get All Transfers")
	Set<Transfer> findAll() {
		transferDao.findAll()
	}
	
	@GraphQLQuery(name = "searchTransfers", description = "Search transfers")
	List<Transfer> searchTransfers(@GraphQLArgument(name = "filter") String filter) {
		transferDao.searchTransfers(filter)
	}
	
	@GraphQLQuery(name = "getTransfersByCase", description = "Transfers by case ID")
	List<Transfer> getTransfersByCase(@GraphQLArgument(name = "id") String id) {
		transferDao.getTransfersByCase(id)
	}
	
	@GraphQLQuery(name = "transfer", description = "Get Transfer By Id")
	Transfer findById(@GraphQLArgument(name = "id") String id) {
		return transferDao.findById(id)
	}
	
	//============== All Mutations ====================
	
	@GraphQLMutation
	Transfer upsertTransfer(
			@GraphQLArgument(name = "id") String id,
			@GraphQLArgument(name = "fields") Map<String, Object> fields
	) {
		
		if (id) {
			def transfer = transferDao.findById(id)
			objectMapper.updateValue(transfer, fields)
			
			def departmentId = fields["departmentId"]
			Department department = departmentDao.findById(departmentId as String)
			transfer.department = department
			
			def roomId = fields["roomId"]
			Room room = roomDao.findById(roomId as String)
			transfer.room = room
			transfer.entryDatetime = LocalDateTime.now()
			
			return transferDao.save(transfer)
		} else {
			def transfer = objectMapper.convertValue(fields, Transfer)
			def departmentId = fields["departmentId"]
			
			Department department = departmentDao.findById(departmentId as String)
			Case pCase = caseDao.findById(fields["caseId"] as String)
			
			transfer.department = department
			transfer.parentCase = pCase
			
			if (fields["roomId"] != null) {
				def roomId = fields["roomId"]
				Room room = roomDao.findById(roomId as String)
				transfer.room = room
				pCase.room = room
			}
			
			def returnVal = transferDao.save(transfer)
			
			pCase.registryType = fields["registryType"] as String
			pCase.department = department
			
			caseDao.save(pCase)
			
			return returnVal
			
		}
	}
}