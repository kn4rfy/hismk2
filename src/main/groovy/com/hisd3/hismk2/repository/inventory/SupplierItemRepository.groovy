package com.hisd3.hismk2.repository.inventory

import com.hisd3.hismk2.domain.inventory.SupplierItem
import org.springframework.data.jpa.repository.JpaRepository

interface SupplierItemRepository extends JpaRepository<SupplierItem, UUID> {

}
