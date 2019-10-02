package com.hisd3.hismk2.repository.inventory

import com.hisd3.hismk2.domain.inventory.SupplierItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface SupplierItemRepository extends JpaRepository<SupplierItem, UUID> {


    @Query(
            value = "Select items from SupplierItem items where items.supplier.id =:id"
    )
    List<SupplierItem> findBySupplier(@Param("id") UUID id)
}
