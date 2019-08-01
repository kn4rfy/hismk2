package com.hisd3.hismk2.repository.patientRepo

import com.hisd3.hismk2.domain.patientDom.NurseNote
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface NurseNoteRepository extends JpaRepository<NurseNote, UUID> {

    @Query(value = "Select nn from NurseNote nn")
    List<NurseNote> getNurseNotes()

    @Query(value = "Select nn from NurseNote nn where pvs.patientCase = :patientCase")
    List<NurseNote> getNurseNotesByCase(@Param("patientCase") String patientCase)
}