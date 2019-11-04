package com.hisd3.hismk2.dao.ancillary

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.domain.ancillary.OrderSlipItem
import com.hisd3.hismk2.domain.ancillary.Orderslip
import com.hisd3.hismk2.repository.ancillary.OrderSlipItemRepository
import com.hisd3.hismk2.repository.ancillary.OrderslipRepository
import com.hisd3.hismk2.services.GeneratorService
import com.hisd3.hismk2.services.GeneratorType
import groovy.transform.TypeChecked
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@TypeChecked
@Service
@Transactional
class OrderslipDao {
	
	@Autowired
	private OrderslipRepository orderslipRepository
	
	@Autowired
	private ObjectMapper objectMapper
	
	@Autowired
	GeneratorService generatorService
	
	@Autowired
	OrderSlipItemRepository orderSlipItemRepository
	
	@PersistenceContext
	EntityManager entityManager
	
	List<Orderslip> addOrderslip(List<Orderslip> orderslips) {
		
		List<Orderslip> res = []
		orderslips.each {
			it ->
				it.orderSlipNo = generatorService?.getNextValue(GeneratorType.OrderSlip_NO, { i ->
					StringUtils.leftPad(i.toString(), 6, "0")
				})
				it.status = "NEW"
				it.deleted = false
				res.add(orderslipRepository.save(it))
		}
		return res
	}
	
	List<OrderSlipItem> insertOrderTransaction(Orderslip orderSlip, List<OrderSlipItem> orderItems) {
		
		List<OrderSlipItem> res = []
		orderSlip.orderSlipNo = generatorService?.getNextValue(GeneratorType.OrderSlip_NO, { i -> StringUtils.leftPad(i.toString(), 6, "0") })
		orderSlip.status = "NEW"
		orderSlip.deleted = false
		Orderslip ret = orderslipRepository.save(orderSlip)
//		def items
//		items = orderItems as List<OrderSlipItem>
		orderItems.each {
			it ->
				it.itemNo = generatorService?.getNextValue(GeneratorType.OrdserSlipItem_NO, { i -> StringUtils.leftPad(i.toString(), 6, "0") })
				it.orderslip = ret
				res.add(orderSlipItemRepository.save(it))
		}
		return res
	}
	
	Orderslip save(Orderslip oSlip) {
		orderslipRepository.save(oSlip)
	}
}
