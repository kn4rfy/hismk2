package com.hisd3.hismk2.config.graphql

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.coxautodev.graphql.tools.SchemaParser
import com.hisd3.hismk2.dao.PatientDao
import com.hisd3.hismk2.domain.Patient
import com.hisd3.hismk2.repository.PatientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class Query implements GraphQLQueryResolver{

    @Autowired
    PatientDao patientDao

    String version(){
        return "1.0.0"
    }

    String ping(String payload){
        return "Hello $payload"
    }


    //<editor-fold desc= "From  graphql/pms.graphqls">
    List<Patient> getPatients( String filtername, Integer page ,Integer size){

    }

    //</editor-fold>

}
