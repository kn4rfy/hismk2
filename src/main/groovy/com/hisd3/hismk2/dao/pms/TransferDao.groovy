package com.hisd3.hismk2.dao.pms

import com.hisd3.hismk2.domain.pms.Transfer
import com.hisd3.hismk2.repository.pms.TransferRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import java.time.LocalDateTime

@Service
@Transactional
class TransferDao {
	
	@Autowired
	private TransferRepository transferRepository
	
	@PersistenceContext
	EntityManager entityManager
	
	List<Transfer> findAll() {
		return transferRepository.findAll()
	}
	
	List<Transfer> searchTransfers(String filter) {
		return transferRepository.searchTransfers(filter)
	}
	
	List<Transfer> getTransfersByCase(String id) {
		return transferRepository.getTransfersByCase(UUID.fromString(id))
	}
	
	List<Transfer> getTransfersByDateRange(LocalDateTime fromDate, LocalDateTime toDate, String registryType) {
		return transferRepository.getTransfersByDateRange(fromDate, toDate, registryType)
	}
	
	Transfer findById(String id) {
		return transferRepository.findById(UUID.fromString(id)).get()
	}
	
	Transfer save(Transfer transfer) {
		transferRepository.save(transfer)
	}
}