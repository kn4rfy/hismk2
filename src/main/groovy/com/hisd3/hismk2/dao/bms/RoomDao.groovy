package com.hisd3.hismk2.dao.bms

import com.hisd3.hismk2.domain.bms.Room
import com.hisd3.hismk2.repository.bms.RoomRepository
import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@TypeChecked
@Service
@Transactional
class RoomDao {
	
	@Autowired
	private RoomRepository roomRepository
	
	@PersistenceContext
	EntityManager entityManager
	
	List<Room> findAll() {
		return roomRepository.findAll()
	}
	
	Room findById(UUID id) {
		return roomRepository.findById(id).get()
	}
	
	List<Room> getAvailableRooms() {
		return roomRepository.getAvailableRooms()
	}
}
