package com.hisd3.hismk2.graphqlservices.patient

import com.hisd3.hismk2.dao.patientDao.VitalsignDao
import com.hisd3.hismk2.domain.Patient
import com.hisd3.hismk2.domain.patientDom.Vitalsign
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
class VitalsignService {

    @Autowired
    VitalsignDao vitalsignDao

    //============== All Queries ====================
    @GraphQLQuery(name = "vitalsigns", description = "Get all patient vital signs")
    List<Vitalsign> getVitalsigns() {
        vitalsignDao.getVitalsigns()
    }

    @GraphQLQuery(name = "vitalsignsByCase", description = "Get patient vital signs by case")
    List<Vitalsign> getVitalsignsByCase(@GraphQLArgument(name = "patientCase") String patientCase) {
        vitalsignDao.getVitalsignsByCase(patientCase)
    }
}
