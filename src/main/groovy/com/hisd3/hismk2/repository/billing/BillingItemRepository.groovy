package com.hisd3.hismk2.repository.billing

import com.hisd3.hismk2.domain.billing.BillingItem
import org.springframework.data.jpa.repository.JpaRepository

interface BillingItemRepository extends JpaRepository<BillingItem, UUID> {

}