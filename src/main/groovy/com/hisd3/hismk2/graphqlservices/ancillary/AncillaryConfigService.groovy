package com.hisd3.hismk2.graphqlservices.ancillary

import com.hisd3.hismk2.dao.ancillary.AncillaryConfigDao
import com.hisd3.hismk2.domain.ancillary.AncillaryConfig
import com.hisd3.hismk2.repository.ancillary.AncillaryConfigRepository
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@TypeChecked
@Component
@GraphQLApi
class AncillaryConfigService {

   @Autowired
   AncillaryConfigDao ancillaryConfigDao

    @GraphQLQuery(name = "getConfigByEntityName", description = "Search Config")
    List<AncillaryConfig> getConfigByName(@GraphQLArgument(name = "name") String name) {
        ancillaryConfigDao.getConfigByName(name)
    }
}
