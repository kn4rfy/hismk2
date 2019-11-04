package com.hisd3.hismk2.repository.inventory

import com.hisd3.hismk2.domain.inventory.PurchaseRequestItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PurchaseRequestItemRepository extends JpaRepository<PurchaseRequestItem, UUID> {
	
	@Query(value = "select p from PurchaseRequestItem p where p.refPr.id = :refPr")
	List<PurchaseRequestItem> getByPrId(@Param("refPr") UUID refPr)
	
	@Query(value = "select p from PurchaseRequestItem p where p.refPo is null")
	List<PurchaseRequestItem> getPrItemByPoNull()
	
	@Query(value = "select p from PurchaseRequestItem p where p.refPo is null and p.refPr.status = 'APPROVED'")
	List<PurchaseRequestItem> getPrItemByWherePoIsNotNullandStatusIsApproved()
	
	@Query(value = "select p from PurchaseRequestItem p where p.refItem.id = :item_id and p.refPo is null and p.refPr.status = 'APPROVED'")
	List<PurchaseRequestItem> getAllByItemIdWhereStatusIsApproved(@Param("item_id") UUID itemId)

	@Query(value="select pi from PurchaseRequestItem pi where pi.refPr.prNo = :prNo")
	List<PurchaseRequestItem> findByPrNo(@Param('prNo') String prNo)
}