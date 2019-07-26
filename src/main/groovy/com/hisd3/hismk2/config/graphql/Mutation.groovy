package com.hisd3.hismk2.config.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.hisd3.hismk2.dao.PatientDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class Mutation implements GraphQLMutationResolver{

    @Autowired
    PatientDao patientDao


    //<editor-fold desc= "From  graphql/pms.graphqls">




    //</editor-fold>

}
