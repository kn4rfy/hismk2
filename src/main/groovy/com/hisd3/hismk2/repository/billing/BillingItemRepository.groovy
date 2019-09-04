package com.hisd3.hismk2.repository.billing

import com.hisd3.hismk2.domain.billing.BillingItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface BillingItemRepository extends JpaRepository<BillingItem, UUID> {
    @Query("select b from BillingItem b where b.billing.id = :billingid")
    List<BillingItem> getBillingItemsByBillingId(@Param("billingid") UUID billingid)
}