package com.hisd3.hismk2.repository.pms

import com.hisd3.hismk2.domain.pms.DoctorOrderProgressNote
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface DoctorOrderProgressNoteRepository extends JpaRepository<DoctorOrderProgressNote, UUID> {
	
	@Query(value = "Select doctorOrderProgressNote from DoctorOrderProgressNote doctorOrderProgressNote where doctorOrderProgressNote.doctorOrder.id = :doctorOrder")
	List<DoctorOrderProgressNote> getDoctorOrderProgressNotesByDoctorOrder(@Param("doctorOrder") UUID doctorOrder)
}