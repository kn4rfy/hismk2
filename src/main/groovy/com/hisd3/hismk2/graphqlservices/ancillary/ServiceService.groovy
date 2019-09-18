package com.hisd3.hismk2.graphqlservices.ancillary

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.dao.ancillary.PanelContentDao
import com.hisd3.hismk2.dao.ancillary.ServiceDao
import com.hisd3.hismk2.domain.ancillary.PanelContent
import com.hisd3.hismk2.domain.ancillary.Service as HisService
import com.hisd3.hismk2.repository.ancillary.ServiceRepository
import com.hisd3.hismk2.services.GeneratorService
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLMutation
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@TypeChecked
@Component
@GraphQLApi
class ServiceService {

	@Autowired
	PanelContentDao panelContentDao

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
	Set<HisService> findAll() {
		servicesDao.findAll()
	}

	@GraphQLQuery(name = "searchServices", description = "Search Services")
	List<HisService> searchServices(@GraphQLArgument(name = "filter") String filter) {
		servicesDao.searchHisServices(filter)
	}

	@GraphQLQuery(name = "getPanelComponent", description = "Search Child Services")
	List<PanelContent> getPanelComponent(@GraphQLArgument(name = "id") String id) {
		panelContentDao.searchHisServices(id)
	}

	@GraphQLQuery(name = "Service", description = "Get Service By Id")
	HisService findById(@GraphQLArgument(name = "id") String id) {

		return servicesDao.findById(UUID.fromString(id))
	}

	//============== All Mutations ====================

	@GraphQLMutation
	HisService upsertServices(
			@GraphQLArgument(name = "id") String id,
			@GraphQLArgument(name = "fields") Map<String, Object> fields
	) {
		if (id) {
			def serviceitem = servicesDao.findById(UUID.fromString(id))
			objectMapper.updateValue(serviceitem, fields)

			serviceitem.available = true

			return servicesDao.save(serviceitem)
		} else {
			def serviceitem = objectMapper.convertValue(fields, HisService)

			serviceitem.available = true

			return servicesDao.save(serviceitem)
		}
	}

	@GraphQLMutation
	List<PanelContent> addPanelComponents(
			@GraphQLArgument(name = "id") String id,
			@GraphQLArgument(name = "fields") ArrayList<Map<String, Object>> fields
	) {

		HisService parentService = servicesDao.findById(UUID.fromString(id))

		List<PanelContent> serviceComponents = []
		def child
		child = fields as  ArrayList<PanelContent>
		child.each {
			it ->
				def order = objectMapper.convertValue(it, PanelContent)

				order.parent = parentService
				order.processCode=it.processCode
				order.serviceName=it.serviceName
				order.id = null
				serviceComponents.add(order)
		}
		return panelContentDao.addPanelComponents(serviceComponents)
	}
}
