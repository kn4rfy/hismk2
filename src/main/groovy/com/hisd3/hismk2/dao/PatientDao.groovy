package com.hisd3.hismk2.dao

import com.hisd3.hismk2.domain.Patient
import com.hisd3.hismk2.repository.PatientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PatientDao {
    @Autowired
    PatientRepository patientRepository



}
