package com.hisd3.hismk2.graphqlservices.ancillary

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.dao.OrderslipDao
import com.hisd3.hismk2.domain.ancillary.Orderslip
import com.hisd3.hismk2.services.GeneratorService
import com.hisd3.hismk2.services.GeneratorType
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLMutation
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.time.LocalDateTime

@Component
@GraphQLApi
class OrderslipService {
	
	@Autowired
	OrderslipDao orderslipDao
	
	@Autowired
	GeneratorService generatorService
	
	@Autowired
	ObjectMapper objectMapper
	
	//============== All Queries ====================
	
	@GraphQLQuery(name = "orderslips", description = "Get All Orderslips")
	List<Orderslip> findAll() {
		orderslipDao.findAll()
	}

	@GraphQLQuery(name = "orderslipsByCase", description = "Get All Orderslips by case")

	List<Orderslip> findByCase(@GraphQLArgument(name = "id") String id) {

		return orderslipDao.findByCase(id)
	}


	//============== All Mutations ====================

	@GraphQLMutation
	Orderslip addOrderslip(
			@GraphQLArgument(name = "fields") Map<String, Object> fields
	){
		println(fields)
		return orderslipDao.addOrderslip(fields)
	}

}
