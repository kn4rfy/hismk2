package com.hisd3.hismk2.graphqlservices.inventory

import com.hisd3.hismk2.domain.inventory.PurchaseOrder
import com.hisd3.hismk2.domain.inventory.PurchaseOrderItems
import com.hisd3.hismk2.domain.inventory.PurchaseRequestItem
import com.hisd3.hismk2.repository.inventory.*
import com.hisd3.hismk2.services.GeneratorService
import com.hisd3.hismk2.services.GeneratorType
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLMutation
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
@TypeChecked
class PurchaseOrderService {
	
	@Autowired
	PurchaseOrderRepository purchaseOrderRepository
	
	@Autowired
	PurchaseOrderItemRepository purchaseOrderItemRepository
	
	@Autowired
	PurchaseRequestItemRepository purchaseRequestItemRepository
	
	@Autowired
	PurchaseRequestRepository purchaseRequestRepository
	
	@Autowired
	SupplierRepository supplierRepository
	
	@Autowired
	GeneratorService generatorService
	
	@GraphQLQuery(name = "poList", description = "list of all purchase order")
	List<PurchaseOrder> poList() {
		return purchaseOrderRepository.findAll()
	}
	
	@GraphQLMutation(name = "upSertPurchaseOrder", description = "upsert po")
	PurchaseOrder upSertPurchaseaOrder(@GraphQLArgument(name = "fields") Map<String, Object> fields, @GraphQLArgument(name = "items") List<Map<String, Object>> items) {
		PurchaseOrder purchaseOrder = new PurchaseOrder()
		
		if (fields.get('id')) {
			purchaseOrder = purchaseOrderRepository.findById(UUID.fromString(fields.get('id').toString())).get()
			
		} else {
			purchaseOrder.supplier = supplierRepository.findById(UUID.fromString(fields.get('supplier').toString())).get()
			purchaseOrder.paymentTerms = fields.get('paymentTerms').toString()
			purchaseOrder.deliveryTerms = fields.get('deliveryTerms').toString()
			purchaseOrder.poNumber = generatorService?.getNextValue(GeneratorType.PO_NO, { i ->
				StringUtils.leftPad(i.toString(), 6, "0")
			})
			PurchaseOrder pOrder = purchaseOrderRepository.save(purchaseOrder)
			if (items.size() != 0) {
				items.eachWithIndex { Map<String, Object> entry, int i ->
					def dto = new PurchaseOrderItems()
					dto.item = UUID.fromString(entry.get('refItemId').toString())
					dto.purchaseOrder = purchaseOrder.id
					dto.quantity = entry.get('qty') as Integer
					dto.supplierLastPrice = entry.get('pkg_price') as Integer
					def prNo = entry.get('prNo').toString().split(',')
					prNo.eachWithIndex { String prNos, int idx ->
						List<PurchaseRequestItem> prItems = purchaseRequestItemRepository.findByPrNo(prNos)
						
						prItems.eachWithIndex { PurchaseRequestItem prItem, int prIdx ->
							if (prItem.refItem.id == dto.item) {
								prItem.refPo = pOrder
								purchaseRequestItemRepository.save(prItem)
							}
						}
					}
					
					purchaseOrderItemRepository.save(dto)
				}
			}
		}
		
		return purchaseOrder
	}
}
