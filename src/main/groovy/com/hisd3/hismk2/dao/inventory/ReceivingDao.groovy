package com.hisd3.hismk2.dao.inventory

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.domain.inventory.InventoryLedger
import com.hisd3.hismk2.domain.inventory.Item
import com.hisd3.hismk2.domain.inventory.ReceivingReport
import com.hisd3.hismk2.domain.inventory.ReceivingReportItem
import com.hisd3.hismk2.repository.inventory.InventoryLedgerRepository
import com.hisd3.hismk2.repository.inventory.ItemRepository
import com.hisd3.hismk2.repository.inventory.ReceivingReportItemRepository
import com.hisd3.hismk2.repository.inventory.ReceivingReportRepository
import com.hisd3.hismk2.services.GeneratorService
import com.hisd3.hismk2.services.GeneratorType
import groovy.transform.TypeChecked
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@TypeChecked
@Service
@Transactional
class ReceivingDao {
	@Autowired
	ReceivingReportRepository receivingReportRepository

	@Autowired
	ItemRepository itemRepository

	@Autowired
	ReceivingReportItemRepository receivingReportItemRepository

	@Autowired
	InventoryLedgerRepository inventoryLedgerRepository

	@Autowired
	ObjectMapper objectMapper

	@Autowired
	GeneratorService generatorService

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

			receivingReport.rrNo = generatorService.getNextValue(GeneratorType.RR_NO) { Long no ->
				StringUtils.leftPad(no.toString(), 5, "0")
			}

			ReceivingReport receivingReportAfterSave = receivingReportRepository.save(receivingReport)

			receivingReport.receivingItems.each {
				ReceivingReportItem it ->
					if (!it.id) {
						it.receivingReport = receivingReportAfterSave
						def receivingItemAfterSave = receivingReportItemRepository.save(it)
						def inventoryLedger = new InventoryLedger()
						Item item = itemRepository.findById(UUID.fromString(it.item)).get()
						inventoryLedger.item = item
						inventoryLedger.quantity = it.qtyDelivered
						inventoryLedgerRepository.save(inventoryLedger)
					}

			}
			
			return receivingReportAfterSave
		}
		
	}
	
	ReceivingReport deleteItems(UUID id) {
		def recevingReportItem = receivingReportItemRepository.findById(id).get()
		def receivingReport = recevingReportItem.receivingReport
		receivingReportItemRepository.delete(recevingReportItem)
		return receivingReport
	}
	
	ReceivingReport delete(UUID id) {
		def receivingReport = receivingReportRepository.findById(id).get()
		
		receivingReportRepository.delete(receivingReport)
	}
}
