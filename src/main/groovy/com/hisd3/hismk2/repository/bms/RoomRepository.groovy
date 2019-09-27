package com.hisd3.hismk2.repository.bms

import com.hisd3.hismk2.domain.bms.Room
import groovy.transform.TypeChecked
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

@TypeChecked
interface RoomRepository extends JpaRepository<Room, UUID> {
	
	@Query(
			value = "Select room from Room room where room.status = 'AVAILABLE'"
	)
	List<Room> getAvailableRooms()
	
	@Query(
			value = "Select room from Room room where upper(room.roomName) like upper(concat('%',:filter,'%'))"
	)
	List<Room> getRoomsByFilter(@Param("filter") String filter)
}
