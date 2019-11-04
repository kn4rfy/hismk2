package com.hisd3.hismk2.graphqlservices.ancillary

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.dao.ancillary.OrderslipDao
import com.hisd3.hismk2.domain.ancillary.OrderSlipItem
import com.hisd3.hismk2.domain.ancillary.Orderslip
import com.hisd3.hismk2.repository.ancillary.OrderSlipItemRepository
import com.hisd3.hismk2.repository.ancillary.OrderslipRepository
import com.hisd3.hismk2.services.GeneratorService
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
class OrderslipService {
	
	@Autowired
	private OrderslipRepository orderslipRepository
	
	@Autowired
	private OrderSlipItemRepository orderSlipItemRepository
	
	@Autowired
	OrderslipDao orderslipDao
	
	@Autowired
	GeneratorService generatorService
	
	@Autowired
	ObjectMapper objectMapper
	
	//============== All Queries ====================
	
	@GraphQLQuery(name = "orderslips", description = "Get All Orderslips")
	List<Orderslip> findAll() {
		orderslipRepository.findAll()
	}
	
	@GraphQLQuery(name = "orderSlip", description = "Get OrderSlip By Id")
	Orderslip findById(@GraphQLArgument(name = "id") UUID id) {
		return orderslipRepository.findById(id).get()
	}
	
	@GraphQLQuery(name = "orderslipsByPatientType", description = "Get All Orderslips by Department")
	List<Orderslip> orderslipsByPatientType(@GraphQLArgument(name = "type") String type = "", @GraphQLArgument(name = "filter") String filter = "") {
		orderslipRepository.filterByPatientType(type, filter)
	}
	
	@GraphQLQuery(name = "orderSlipsByCase", description = "Get All Order Slips by Case")
	List<Orderslip> findByCase(@GraphQLArgument(name = "id") UUID id) {
		orderslipRepository.getOrderslipsByCase(id)
	}
	
	@GraphQLQuery(name = "orderSlipsByNo", description = "Get All Orderslips by OrderSlip No")
	List<Orderslip> getByOrderSlipNo(@GraphQLArgument(name = "orderSlipNo") String orderSlipNo) {
		orderslipRepository.getByOrderSlipNo(orderSlipNo)
	}
	
	@GraphQLQuery(name = "orderSlipsByDepartment", description = "Get All Orderslips by Department")
	List<Orderslip> getByOrderSlipDepartment(@GraphQLArgument(name = "departmentId") UUID departmentId) {
		orderslipRepository.getByOrderSlipDepartment(departmentId)
	}
	
	@GraphQLQuery(name = "orderSlipItems", description = "Get all OrderSlip Items")
	List<OrderSlipItem> getOrderSlipItems(@GraphQLContext Orderslip orderslip) {
		orderSlipItemRepository.getByOrderSlip(orderslip.id).sort { it.created }
	}
	
	//============== All Mutations ====================
	
	@GraphQLMutation
	List<OrderSlipItem> addOrderslip(
			@GraphQLArgument(name = "fields") Map<String, Object> fields
	) {
		//println(fields)
		
		List<OrderSlipItem> orderslipItem = []
		def oSlip
		oSlip = objectMapper.convertValue(fields.get("order"), Orderslip) as Orderslip
		
		def ordersItems
		ordersItems = fields.get("requested") as ArrayList<OrderSlipItem>
		ordersItems.each {
			it ->
				def order = objectMapper.convertValue(it, OrderSlipItem)
				order.posted = false
				order.status = "NEW"
				order.deleted = false
				orderslipItem.add(order)
		}
		
		return orderslipDao.insertOrderTransaction(oSlip, orderslipItem)
	}
}

