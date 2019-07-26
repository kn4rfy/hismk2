package com.hisd3.hismk2.dao

import com.hisd3.hismk2.domain.Patient
import com.hisd3.hismk2.repository.PatientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class PatientDao {
    @Autowired
    PatientRepository patientRepository

    List<Patient> getPatients( String filtername, Integer page ,Integer size){
         return patientRepository.getPatients(filtername,new PageRequest(
                 page,size
         )).collect()
    }
    Patient getPatient(UUID id){
        return patientRepository.findById(id).get()
    }


    void upsertPatient(UUID id,
                       String patientNo,
    String lastname,
    String middlename,
    String nameSuffix,
    String address,
    String country,
    String stateProvince,
    String cityMunicipality,
    String barangay,
    String gender,
    String dob,
    String allergies,
    String father,
    String mother,
    String fatherOccupation,
    String motherOccupation,
    String emergencyContactName,
    String emergencyContactAddress,
    String emergencyContactRelationship,
    String emegencyContactNo,
    String guarantorName,
    String guarantorAddress,
    String guarantorRelationship,
    String guarantorContactNo){


         if(id == null){

         }
        else {

         }
    }
}
