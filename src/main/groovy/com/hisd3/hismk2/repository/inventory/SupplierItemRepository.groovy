package com.hisd3.hismk2.repository.inventory

import com.hisd3.hismk2.domain.inventory.SupplierItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface SupplierItemRepository extends JpaRepository<SupplierItem, UUID> {
	@Query(
			value = '''Select items from SupplierItem items where items.supplier.id =:id and
            (lower(items.item.descLong) like concat('%',:filter,'%') or 
            lower(items.item.genericName) like concat('%',:filter,'%'))'''
	)
	List<SupplierItem> findBySupplier(@Param("id") UUID id, @Param("filter") String filter)
	
	@Query(value = "select s from SupplierItem s where s.item.id = :item_id")
	List<SupplierItem> findByItem(@Param("item_id") UUID itemId)
	
	@Query(value = "select s from SupplierItem s where s.supplier.id = :supplier_id")
	List<SupplierItem> findBySupplierId(@Param("supplier_id") UUID supplierId)
}
