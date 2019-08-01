package com.hisd3.hismk2.repository.pms

import com.hisd3.hismk2.domain.pms.NurseNote
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface NurseNoteRepository extends JpaRepository<NurseNote, UUID> {
	
	@Query(value = "Select nn from NurseNote nn")
	List<NurseNote> getNurseNotes()
	
	@Query(value = "Select nn from NurseNote nn where nn.patientCase = :patientCase")
	List<NurseNote> getNurseNotesByPatientCase(@Param("patientCase") String patientCase)
}