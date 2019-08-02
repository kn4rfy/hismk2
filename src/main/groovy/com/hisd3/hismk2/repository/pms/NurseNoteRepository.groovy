package com.hisd3.hismk2.repository.pms

import com.hisd3.hismk2.domain.pms.NurseNote
import org.springframework.data.jpa.repository.JpaRepository

interface NurseNoteRepository extends JpaRepository<NurseNote, UUID> {

}