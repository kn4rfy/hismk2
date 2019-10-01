package com.hisd3.hismk2.repository.inventory

import com.hisd3.hismk2.domain.inventory.Supplier
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface SupplierRepository extends JpaRepository<Supplier, UUID> {

}
