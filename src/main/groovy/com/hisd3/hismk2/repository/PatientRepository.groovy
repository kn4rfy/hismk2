package com.hisd3.hismk2.repository

import com.hisd3.hismk2.domain.Patient
import org.springframework.data.jpa.repository.JpaRepository

interface PatientRepository extends JpaRepository<Patient, UUID> {

}