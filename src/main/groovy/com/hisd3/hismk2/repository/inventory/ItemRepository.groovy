package com.hisd3.hismk2.repository.inventory

import com.hisd3.hismk2.domain.inventory.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ItemRepository extends JpaRepository<Item, UUID> {
	
	@Query(value = "Select item from Item item where item.isMedicine = true and item.active = true")
	List<Item> findAllMedicines()
	
	@Query(value = '''Select item from Item item where  
					  lower(item.descLong) like lower(concat('%',:filter,'%')) or  
					  lower(item.barcode) like lower(concat('%',:filter,'%'))
			''')
	
	List<Item> itemsByFilter(@Param("filter") String filter)
}
