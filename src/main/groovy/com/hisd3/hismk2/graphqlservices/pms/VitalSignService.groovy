package com.hisd3.hismk2.graphqlservices.pms

import com.hisd3.hismk2.repository.pms.VitalSignRepository
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
class VitalSignService {
	
	@Autowired
	private VitalSignRepository vitalSignRepository
	
	//============== All Queries ====================
}
