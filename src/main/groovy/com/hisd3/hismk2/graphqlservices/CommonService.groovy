package com.hisd3.hismk2.graphqlservices

import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.stereotype.Component

@Component
@GraphQLApi
class CommonService {
	
	@GraphQLQuery
	String version() {
		return "1.0.0"
	}
}
