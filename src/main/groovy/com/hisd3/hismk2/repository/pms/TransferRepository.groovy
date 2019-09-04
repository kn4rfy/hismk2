package com.hisd3.hismk2.repository.pms

import com.hisd3.hismk2.domain.pms.Transfer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

import java.time.LocalDateTime

interface TransferRepository extends JpaRepository<Transfer, UUID> {
	
	@Query(value = "Select t from Transfer t order by t.entryDatetime")
	List<Transfer> searchTransfers(@Param("filter") String filter)
	
	@Query(value = "Select t from Transfer t where t.parentCase.id = :id order by t.entryDatetime")
	List<Transfer> getTransfersByCase(@Param("id") UUID id)

	@Query(value = "Select t from Transfer t where t.entryDatetime between :fromDate and :toDate and t.registryType = :registryType order by t.entryDatetime")
	List<Transfer> getTransfersByDateRange(@Param("fromDate") LocalDateTime fromDate, @Param("toDate") LocalDateTime toDate, @Param("registryType") String registryType)
}