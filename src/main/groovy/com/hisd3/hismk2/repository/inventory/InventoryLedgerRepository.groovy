package com.hisd3.hismk2.repository.inventory

import com.hisd3.hismk2.domain.inventory.InventoryLedger
import org.springframework.data.jpa.repository.JpaRepository

interface InventoryLedgerRepository extends JpaRepository<InventoryLedger, UUID> {

}