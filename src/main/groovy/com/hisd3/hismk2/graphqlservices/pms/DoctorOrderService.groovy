package com.hisd3.hismk2.graphqlservices.pms

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.domain.pms.DoctorOrder
import com.hisd3.hismk2.domain.pms.DoctorOrderItem
import com.hisd3.hismk2.domain.pms.DoctorOrderProgressNote
import com.hisd3.hismk2.repository.pms.DoctorOrderItemRepository
import com.hisd3.hismk2.repository.pms.DoctorOrderProgressNoteRepository
import com.hisd3.hismk2.repository.pms.DoctorOrderRepository
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLContext
import io.leangen.graphql.annotations.GraphQLMutation
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@TypeChecked
@Component
@GraphQLApi
class DoctorOrderService {
	
	@Autowired
	private DoctorOrderRepository doctorOrderRepository
	
	@Autowired
	private DoctorOrderProgressNoteRepository doctorOrderProgressNoteRepository
	
	@Autowired
	private DoctorOrderItemRepository doctorOrderItemRepository
	
	@Autowired
	ObjectMapper objectMapper
	
	//============== All Queries ====================
	
	@GraphQLQuery(name = "doctorOrders", description = "Get all DoctorOrders")
	List<DoctorOrder> findAll() {
		return doctorOrderRepository.findAll().sort { it.entryDateTime }
	}
	
	@GraphQLQuery(name = "doctorOrder", description = "Get DoctorOrder By Id")
	DoctorOrder findById(@GraphQLArgument(name = "id") UUID id) {
		return doctorOrderRepository.findById(id).get()
	}
	
	@GraphQLQuery(name = "doctorOrderByCase", description = "Get all DoctorOrders by Case Id")
	List<DoctorOrder> getDoctorOrdersByCase(@GraphQLArgument(name = "caseId") UUID caseId) {
		return doctorOrderRepository.getDoctorOrdersByCase(caseId).sort { it.entryDateTime }
	}
	
	@GraphQLQuery(name = "doctorOrderProgressNotes", description = "Get all DoctorOrder DoctorOrderProgressNotes")
	List<DoctorOrderProgressNote> getDoctorOrderProgressNotesByDoctorOrder(@GraphQLContext DoctorOrder doctorOrder) {
		return doctorOrderProgressNoteRepository.getDoctorOrderProgressNotesByDoctorOrder(doctorOrder.id).sort { it.entryDateTime }
	}
	
	@GraphQLQuery(name = "doctorOrderItems", description = "Get all DoctorOrder DoctorOrderItems")
	List<DoctorOrderItem> getDoctorOrderItemsByDoctorOrder(@GraphQLContext DoctorOrder doctorOrder) {
		return doctorOrderItemRepository.getDoctorOrderItemsByDoctorOrder(doctorOrder.id).sort { it.entryDateTime }
	}
	
	@GraphQLMutation
	DoctorOrder addDoctorOrder(
			@GraphQLArgument(name = "fields") Map<String, Object> fields
	) {
		DoctorOrder doctorOrder = doctorOrderRepository.save(objectMapper.convertValue(fields.get("doctorOrder"), DoctorOrder))
		
		DoctorOrderProgressNote doctorOrderProgressNote = objectMapper.convertValue(fields.get("doctorOrderProgressNote"), DoctorOrderProgressNote)
		if (doctorOrderProgressNote != null) {
			doctorOrderProgressNote.entryDateTime = doctorOrder.entryDateTime
			doctorOrderProgressNote.doctorOrder = doctorOrder
			doctorOrderProgressNoteRepository.save(doctorOrderProgressNote)
		}
		
		List<DoctorOrderItem> doctorOrderItems = fields.get("doctorOrderItems") as ArrayList<DoctorOrderItem>
		
		doctorOrderItems.each {
			it ->
				it = objectMapper.convertValue(it, DoctorOrderItem)
				it.doctorOrder = doctorOrder
				it.status = "PENDING"
				it.entryDateTime = doctorOrder.entryDateTime
				doctorOrderItemRepository.save(it)
		}
		
		return doctorOrder
	}
}
