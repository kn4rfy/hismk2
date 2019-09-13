package com.hisd3.hismk2.repository.inventory

import com.hisd3.hismk2.domain.inventory.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ItemRepository extends JpaRepository<Item, UUID> {
	
	@Query(value = "Select item from Item item where item.isMedicine = true and item.active = true")
	List<Item> findAllMedicines()
}