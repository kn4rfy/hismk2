package com.hisd3.hismk2.repository.pms

import com.hisd3.hismk2.domain.pms.Medication
import org.springframework.data.jpa.repository.JpaRepository

interface MedicationRepository extends JpaRepository<Medication, UUID> {

}