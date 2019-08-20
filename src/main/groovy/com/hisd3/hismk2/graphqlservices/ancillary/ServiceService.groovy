package com.hisd3.hismk2.graphqlservices.ancillary

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.dao.ancillary.ServiceDao
import com.hisd3.hismk2.domain.ancillary.Service
import com.hisd3.hismk2.repository.ancillary.ServiceRepository
import com.hisd3.hismk2.services.GeneratorService
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLMutation
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
class ServiceService {
	
	@Autowired
	ServiceDao servicesDao
	
	@Autowired
	GeneratorService generatorService
	
	@Autowired
	private ServiceRepository servicesRepository
	
	@Autowired
	ObjectMapper objectMapper
	
	//============== All Queries ====================
	
	@GraphQLQuery(name = "services", description = "Get All Service")
	Set<Service> findAll() {
		servicesDao.findAll()
	}
	
	@GraphQLQuery(name = "searchServices", description = "Search Services")
	List<Service> searchServices(@GraphQLArgument(name = "filter") String filter) {
		servicesDao.searchHisServices(filter)
	}
	
	@GraphQLQuery(name = "Service", description = "Get Service By Id")
	Service findById(@GraphQLArgument(name = "id") String id) {
		
		return servicesDao.findById(id)
	}
	
	//============== All Mutations ====================
	
	@GraphQLMutation
	Service upsertServices(
			@GraphQLArgument(name = "id") String id,
			@GraphQLArgument(name = "fields") Map<String, Object> fields
	) {
		if (id) {
			def serviceitem = servicesDao.findById(id)
			objectMapper.updateValue(serviceitem, fields)
			
			serviceitem.available = true
			
			return servicesDao.save(serviceitem)
		} else {
			def serviceitem = objectMapper.convertValue(fields, Service)
			
			serviceitem.available = true
			
			return servicesDao.save(serviceitem)
		}
	}
}
