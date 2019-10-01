package com.hisd3.hismk2.graphqlservices.billing

import com.hisd3.hismk2.dao.billing.BillingDao
import com.hisd3.hismk2.domain.billing.Billing
import com.hisd3.hismk2.domain.billing.BillingItem
import com.hisd3.hismk2.repository.billing.BillingRepository
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLMutation
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@TypeChecked
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
	List<Billing> getBillingByPatient(@GraphQLArgument(name = "patientId") UUID patientId) {
		billingDao.getBillingByPatient(patientId)
	}
	
	@GraphQLQuery
	Billing getBillingById(@GraphQLArgument(name = "billingId") UUID billingId) {
		billingRepository.findById(billingId).get()
	}
	
	@GraphQLQuery
	List<BillingItem> getBillingItemsByBill(@GraphQLArgument(name = "billingId") UUID billingId) {
		billingDao.getBillingItemsByBill(billingId)
	}
	
	@GraphQLMutation
	Billing saveBillingItems(
			@GraphQLArgument(name = "patientId") UUID patientId,
			@GraphQLArgument(name = "caseId") UUID caseId,
			@GraphQLArgument(name = "billingItems") List<Map<String, Object>> billingItemList) {
		billingDao.saveBillingItems(patientId, caseId, null, billingItemList)
	}
	
	@GraphQLMutation
	Billing upsertBilling(
			@GraphQLArgument(name = "patientId") UUID patientId,
			@GraphQLArgument(name = "caseId") UUID caseId,
			@GraphQLArgument(name = "billingId") UUID billingId,
			@GraphQLArgument(name = "billingItems") List<Map<String, Object>> billingItemList) {
		
		billingDao.saveBillingItems(patientId, caseId, billingId, billingItemList)
	}
	
	@GraphQLMutation
	BillingItem toggleBillingItem(@GraphQLArgument(name = "billingItemId") String billingItemId) {
		billingDao.toggleBillingItem(billingItemId)
	}
}
