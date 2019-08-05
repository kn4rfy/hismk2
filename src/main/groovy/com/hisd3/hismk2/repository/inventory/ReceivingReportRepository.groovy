package com.hisd3.hismk2.repository.inventory

import com.hisd3.hismk2.domain.inventory.ReceivingReport
import org.springframework.data.jpa.repository.JpaRepository

interface ReceivingReportRepository extends JpaRepository<ReceivingReport, UUID> {

}