package com.hisd3.hismk2.graphqlservices.billing

import com.hisd3.hismk2.dao.billing.BillingDao
import com.hisd3.hismk2.domain.billing.Billing
import com.hisd3.hismk2.domain.billing.BillingItem
import com.hisd3.hismk2.repository.billing.BillingRepository
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLMutation
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
class BillingService {
	
	@Autowired
	BillingRepository billingRepository

	@Autowired
	BillingDao billingDao

	@GraphQLQuery
	List<Billing> getAllBilling() {
		billingRepository.findAll()
	}

	@GraphQLQuery
	List<Billing> getBillingByPatient(@GraphQLArgument(name="patientid") UUID patientid){
		billingDao.getBillingByPatient(patientid)
	}

	@GraphQLQuery
	List<BillingItem> getBillingItemsByBillingId(@GraphQLArgument(name="billingid") UUID billingid){
		billingDao.getBillingItemsByBillingId(billingid)
	}


	@GraphQLMutation
	Billing saveBillingItems(@GraphQLArgument(name = "patientId") UUID patientId, @GraphQLArgument(name = "billingItems") List<Map<String, Object>> billingItemList){
			billingDao.saveBillingItems(patientId, billingItemList)
	}
}
