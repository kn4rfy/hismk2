package com.hisd3.hismk2.repository.inventory

import com.hisd3.hismk2.domain.inventory.PurchaseRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PurchaseRequestRepository extends JpaRepository<PurchaseRequest, UUID> {
    @Query(value="select pr from PurchaseRequest pr where pr.prNo = :prNo")
    PurchaseRequest getByPrNo(@Param('prNo') String prNo)
}