package com.hisd3.hismk2.repository.bms

import com.hisd3.hismk2.domain.bms.Room
import groovy.transform.TypeChecked
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

@TypeChecked
interface RoomRepository extends JpaRepository<Room, UUID> {
	
	@Query(
			value = "Select r from Room r where r.status = 'AVAILABLE'",
			countQuery = "Select count(r) from Room r where r.status = 'AVAILABLE'"
	)
	List<Room> getAvailableRooms()
}
