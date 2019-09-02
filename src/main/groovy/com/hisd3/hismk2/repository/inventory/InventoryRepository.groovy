package com.hisd3.hismk2.repository.inventory

import com.hisd3.hismk2.domain.inventory.Inventory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface InventoryRepository extends JpaRepository<Inventory, UUID> {
	
	@Query(value = "Select inv from Inventory inv where inv.department.id=:departmentid and upper(inv.item.descLong) like  upper(concat('%',:filter,'%'))")
	List<Inventory> inventoryByDepartmentAndFilter(@Param("departmentid") UUID departmentid, @Param("filter") String filter)
}