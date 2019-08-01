package com.hisd3.hismk2.repository

import com.hisd3.hismk2.domain.Patient
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PatientRepository extends JpaRepository<Patient, UUID> {


    @Query(value = """
    Select p from Patient p where lowercase(p.fullName) like concat('%',:filter,'%')
     """,
            countQuery = """
    Select count(p) from Patient p where lowercase(p.fullName) like concat('%',:filter,'%')
     """)
    Page<Patient> getPatients(@Param("filter") String filter, Pageable pageable)
}