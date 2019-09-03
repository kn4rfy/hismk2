package com.hisd3.hismk2.graphqlservices.bms

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.dao.DepartmentDao
import com.hisd3.hismk2.dao.bms.RoomDao
import com.hisd3.hismk2.domain.Department
import com.hisd3.hismk2.domain.bms.Room
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLMutation
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@TypeChecked
@Component
@GraphQLApi
class RoomService {
	
	@Autowired
	RoomDao roomDao

	@Autowired
	DepartmentDao departmentDao

	@PersistenceContext
	EntityManager entityManager

	@Autowired
	ObjectMapper objectMapper

	//============== All Queries ====================
	
	@GraphQLQuery(name = "rooms", description = "Get all rooms")
	List<Room> findAll() {
		return roomDao.findAll()
	}
	
	@GraphQLQuery(name = "room", description = "Get Roopm By Id")
	Room findById(@GraphQLArgument(name = "id") String id) {
		
		return roomDao.findById(id)
	}
	
	@GraphQLQuery(name = "availableRooms", description = "Get all available rooms")
	List<Room> getAvailableRooms() {
		return roomDao.getAvailableRooms()
	}

	@GraphQLMutation
	Room upsertRoom(
			@GraphQLArgument(name = "id") String id,
			@GraphQLArgument(name = "fields") Map<String, Object> fields
	) {

		def room = objectMapper.convertValue(fields, Room)

		def deptId = fields["departmentId"]

		if (deptId) {
			Department dept = departmentDao.findById(deptId as String)
			room.department = dept
		}

		return roomDao.save(room)
	}
}
