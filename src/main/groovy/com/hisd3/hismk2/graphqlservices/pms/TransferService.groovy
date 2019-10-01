package com.hisd3.hismk2.graphqlservices.pms

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.dao.DepartmentDao
import com.hisd3.hismk2.domain.pms.Transfer
import com.hisd3.hismk2.repository.bms.RoomRepository
import com.hisd3.hismk2.repository.pms.TransferRepository
import com.hisd3.hismk2.services.GeneratorService
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.time.LocalDateTime

@TypeChecked
@Component
@GraphQLApi
class TransferService {
	
	@Autowired
	private TransferRepository transferRepository
	
	@Autowired
	DepartmentDao departmentDao
	
	@Autowired
	private RoomRepository roomRepository
	
	@Autowired
	GeneratorService generatorService
	
	@Autowired
	ObjectMapper objectMapper
	
	//============== All Queries ====================
	
	@GraphQLQuery(name = "transfers", description = "Get All Transfers")
	List<Transfer> findAll() {
		return transferRepository.findAll()
	}
	
	@GraphQLQuery(name = "transfer", description = "Get Transfer By Id")
	Transfer findById(@GraphQLArgument(name = "id") UUID id) {
		return transferRepository.findById(id).get()
	}
	
	@GraphQLQuery(name = "searchTransfers", description = "Search Transfers")
	List<Transfer> searchTransfers(@GraphQLArgument(name = "filter") String filter) {
		return transferRepository.searchTransfers(filter)
	}
	
	@GraphQLQuery(name = "transfersByCase", description = "Transfers by case ID")
	List<Transfer> getTransfersByCase(@GraphQLArgument(name = "id") UUID id) {
		return transferRepository.getTransfersByCase(id)
	}
	
	@GraphQLQuery(name = "census", description = "Get Transfers by Date range")
	List<Transfer> getTransfersByDateRange(@GraphQLArgument(name = "fields") Map<String, Object> fields) {
		def fromDate = fields["fromDate"] as LocalDateTime
		def toDate = fields["toDate"] as LocalDateTime
		def registryType = fields["registryType"] as String
		
		return transferRepository.getTransfersByDateRange(fromDate, toDate, registryType)
	}
}