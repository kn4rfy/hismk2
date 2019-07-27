package com.hisd3.hismk2.graphqlservices

import com.hisd3.hismk2.domain.Patient
import com.hisd3.hismk2.repository.PatientRepository
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLContext
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@GraphQLApi
class PmsService {

    @Autowired
    PatientRepository patientRepository


    @GraphQLQuery(name = "patient", description = "Get Patient By Id")
    Patient getPatient(@GraphQLArgument(name = "id") String id){
        return patientRepository.findById(UUID.fromString(id)).get()

    }

    @GraphQLQuery
    List<String> aliases(@GraphQLContext Patient patient){

        return [patient.lastname, patient.firstname]
    }
}
