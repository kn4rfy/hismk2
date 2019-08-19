package com.hisd3.hismk2.dao.inventory

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.domain.inventory.ReceivingReport
import com.hisd3.hismk2.domain.inventory.ReceivingReportItem
import com.hisd3.hismk2.repository.inventory.ReceivingReportItemRepository
import com.hisd3.hismk2.repository.inventory.ReceivingReportRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Service
@Transactional
class ReceivingDao {
	@Autowired
	ReceivingReportRepository receivingReportRepository
	@Autowired
	ReceivingReportItemRepository receivingReportItemRepository
	@Autowired
	ObjectMapper objectMapper
	@PersistenceContext
	EntityManager entityManager
	
	List<ReceivingReport> findAll() {
		return receivingReportRepository.findAll()
	}
	
	Set<ReceivingReportItem> getReceivingItems(ReceivingReport receivingReport) {
		def mergedItem = entityManager.merge(receivingReport)
		mergedItem.receivingItems.size()
		return mergedItem.receivingItems as Set
	}
	
	ReceivingReport getReceivingReport(UUID id) {
		return receivingReportRepository.findById(id).get()
	}
	
	ReceivingReport save(UUID id, Map<String, Object> fields) {
		
		if (id) {
			def receivingReport = receivingReportRepository.findById(id).get()
			def receivingItems = objectMapper.convertValue(fields, ReceivingReport).receivingItems
			
			if (receivingItems.size() != 0) {
				receivingItems.each {
					ReceivingReportItem it ->
						if (!it.id) {
							it.receivingReport = receivingReport
							receivingReportItemRepository.save(it)
						}
				}
			}
			
			return receivingReportRepository.save(receivingReport)
		} else {
			def receivingReport = objectMapper.convertValue(fields, ReceivingReport)
			
			ReceivingReport receivingReportAfterSave = receivingReportRepository.save(receivingReport)
			
			receivingReport.receivingItems.each {
				ReceivingReportItem it ->
					if (!it.id) {
						it.receivingReport = receivingReportAfterSave
						receivingReportItemRepository.save(it)
					}
				
			}
			
			return receivingReportAfterSave
		}
		
	}
	
	ReceivingReport delete(UUID id) {
		def recevingReport = receivingReportRepository.findById(id)
		
		return receivingReportRepository.delete(recevingReport as ReceivingReport)
	}
}
