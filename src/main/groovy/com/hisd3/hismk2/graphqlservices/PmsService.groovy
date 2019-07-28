package com.hisd3.hismk2.graphqlservices

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.dao.PatientDao
import com.hisd3.hismk2.domain.Patient
import com.hisd3.hismk2.domain.PatientCase
import com.hisd3.hismk2.services.GeneratorService
import com.hisd3.hismk2.services.GeneratorType
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLContext
import io.leangen.graphql.annotations.GraphQLMutation
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.execution.relay.Page
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.awt.print.Pageable

@Component
@GraphQLApi
class PmsService {

    @Autowired
    PatientDao patientDao

    @Autowired
    GeneratorService generatorService

    @Autowired
    ObjectMapper objectMapper



    //============== All Queries ====================

    @GraphQLQuery(name = "patients", description = "Get All Patients")
    Set<Patient> getAllPatients(){
        patientDao.getAllPatients()
    }


    @GraphQLQuery(name = "patientsByPage", description = "Get All Patients By Page")
    Page<Patient> getAllPatientsByPage(@GraphQLArgument(name = "first") int first,
                                       @GraphQLArgument(name = "after") String after="0"){
        patientDao.getPatientRelayPage(first,Integer.parseInt(after))
    }



    @GraphQLQuery(name = "patient", description = "Get Patient By Id")
    Patient getPatient(@GraphQLArgument(name = "id") String id){
        return patientDao.findById(id)

    }

    @GraphQLQuery(name = "patientCases", description = "Get All Patient Cases")
    Set<PatientCase> getPatientCases(@GraphQLContext Patient patient){
        return patientDao.getPatientCases(patient)
    }


    //============== All Mutations ====================
    @GraphQLMutation
    Patient upsertPatient(@GraphQLArgument(name = "id") String id,
                         @GraphQLArgument(name = "fields") Map<String,Object> fields
                               ){

        if(id){

            def patient = patientDao.findById(id)
            objectMapper.updateValue(patient,fields)
            patient = patientDao.save(patient)
            return patient
        }
        else {

            def patient =   objectMapper.convertValue(fields,Patient)
            patient.patientNo = generatorService.getNextValue(GeneratorType.PATIENT_NO){ Long no ->
                 StringUtils.leftPad(no.toString(),5,"0")
            }
            patient = patientDao.save(patient)

            return patient
        }

    }
}