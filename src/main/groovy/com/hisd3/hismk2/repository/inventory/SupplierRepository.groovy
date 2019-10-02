package com.hisd3.hismk2.repository.inventory

import com.hisd3.hismk2.domain.inventory.Supplier
import com.hisd3.hismk2.domain.inventory.SupplierItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface SupplierRepository extends JpaRepository<Supplier, UUID> {



    @Query(value = '''Select s from Supplier s where
            lower(s.supplierName) like concat('%',:filter,'%') or 
            lower(s.supplierCode) like concat('%',:filter,'%')''')

    List<Supplier> findAllByFilter(@Param("filter") String filter)

}
