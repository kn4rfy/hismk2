package com.hisd3.hismk2.graphqlservices.bms

import com.hisd3.hismk2.dao.bms.RoomDao
import com.hisd3.hismk2.domain.bms.Room
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLArgument
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
	
	@PersistenceContext
	EntityManager entityManager
	
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
}
