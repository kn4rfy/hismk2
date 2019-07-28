package com.hisd3.hismk2.graphqlservices

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.hisd3.hismk2.domain.Patient
import com.hisd3.hismk2.repository.PatientRepository
import com.hisd3.hismk2.services.GeneratorService
import com.hisd3.hismk2.services.GeneratorType
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLContext
import io.leangen.graphql.annotations.GraphQLMutation
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sun.jvm.hotspot.runtime.ObjectMonitor

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
@GraphQLApi
@Transactional
class PmsService {

    @Autowired
    PatientRepository patientRepository

    @Autowired
    GeneratorService generatorService

    @Autowired
    ObjectMapper objectMapper


    @GraphQLQuery(name = "patient", description = "Get Patient By Id")
    Patient getPatient(@GraphQLArgument(name = "id") String id){
        return patientRepository.findById(UUID.fromString(id)).get()

    }


    @GraphQLMutation
    Patient upsertPatient(@GraphQLArgument(name = "id") String id,
                         @GraphQLArgument(name = "fields") Map<String,Object> fields
                               ){




        if(id){


            def patient = patientRepository.findById(UUID.fromString(id)).get()
            objectMapper.updateValue(patient,fields)

            patient = patientRepository.save(patient)

            return patient
        }
        else {

            def patient =   objectMapper.convertValue(fields,Patient)
            patient.patientNo = generatorService.getNextValue(GeneratorType.PATIENT_NO){ Long no ->
                 StringUtils.leftPad(no.toString(),5,"0")
            }
            patient = patientRepository.save(patient)

            return patient

        }


    }
}
