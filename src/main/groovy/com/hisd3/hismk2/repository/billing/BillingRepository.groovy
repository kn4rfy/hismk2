package com.hisd3.hismk2.repository.billing

import com.hisd3.hismk2.domain.billing.Billing
import org.springframework.data.jpa.repository.JpaRepository

interface BillingRepository extends JpaRepository<Billing, UUID>{

}