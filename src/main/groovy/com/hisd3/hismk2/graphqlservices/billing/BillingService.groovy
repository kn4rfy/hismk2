package com.hisd3.hismk2.graphqlservices.billing

import com.hisd3.hismk2.domain.billing.Billing
import com.hisd3.hismk2.repository.billing.BillingRepository
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
class BillingService {
	
	@Autowired
	BillingRepository billingRepository
	
	@GraphQLQuery
	List<Billing> getAllBilling() {
		billingRepository.findAll()
	}
}
