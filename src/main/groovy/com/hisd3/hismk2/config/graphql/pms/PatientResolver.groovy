package com.hisd3.hismk2.config.graphql.pms

import com.coxautodev.graphql.tools.GraphQLResolver
import com.hisd3.hismk2.domain.Patient
import org.springframework.stereotype.Component

@Component
class PatientResolver implements GraphQLResolver<Patient> {

    // Just a sample property Resolver for Patient
    List<String> aliases(Patient patient){

        return ["Apple","Mango","Orange"]
    }



}
