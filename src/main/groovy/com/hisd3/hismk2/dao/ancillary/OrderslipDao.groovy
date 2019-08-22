package com.hisd3.hismk2.dao

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.domain.ancillary.Orderslip
import com.hisd3.hismk2.repository.ancillary.OrderslipRepository
import com.hisd3.hismk2.services.GeneratorService
import com.hisd3.hismk2.services.GeneratorType
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
@Transactional
class OrderslipDao {
	
	@Autowired
	private OrderslipRepository orderslipRepository
	
	@Autowired
	private ObjectMapper objectMapper
	
	@Autowired
	GeneratorService generatorService
	
	@PersistenceContext
	EntityManager entityManager
	
	List<Orderslip> findAll() {
		return orderslipRepository.findAll()
	}
	
	Orderslip findById(String id) {
		return orderslipRepository.findById(Long.parseLong(id)).get()
	}
	
	List<Orderslip> findByCase(String id) {
		
		orderslipRepository.findByCase(UUID.fromString(id))
	}
	
	Orderslip addOrderslip(Map<String, Object> fields) {
		
		def items
		items = fields.get("requested") as ArrayList<Orderslip>
		
		items.each {
			Map<String, Object> it ->
				def item = objectMapper.convertValue(it, Orderslip)
				
				item.orderslipNo = generatorService?.getNextValue(GeneratorType.OrderSlip_NO, { i ->
					StringUtils.leftPad(i.toString(), 6, "0")
				})
				item.submittedViaHl7 = false
				item.posted = false
				item.status = "NEW"
				item.deleted = false
				orderslipRepository.save(item)
				System.out.println(item.id)
		}
		
		return
		
	}
	
	Orderslip save(Orderslip oSlip) {
		orderslipRepository.save(oSlip)
	}
}
