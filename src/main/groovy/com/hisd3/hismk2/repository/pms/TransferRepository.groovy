package com.hisd3.hismk2.repository.pms

import com.hisd3.hismk2.domain.pms.Transfer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface TransferRepository extends JpaRepository<Transfer, UUID> {
	
	@Query(value = "Select t from Transfer t")
	List<Transfer> searchTransfers(@Param("filter") String filter)
	
	@Query(value = "Select t from Transfer t where t.caseNo = :caseNo")
	List<Transfer> getTransfersByCase(@Param("caseNo") String caseNo)
}