package com.hisd3.hismk2.repository.inventory

import com.hisd3.hismk2.domain.inventory.ReceivingReportItem
import org.springframework.data.jpa.repository.JpaRepository

interface ReceivingReportItemRepository extends JpaRepository<ReceivingReportItem, UUID>{


}