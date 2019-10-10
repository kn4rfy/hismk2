package com.hisd3.hismk2.dao.ancillary

import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.dao.ancillary.dto.DiagnosticsResultsDto
import com.hisd3.hismk2.domain.Department
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

	List<Orderslip> findAll() {
		return orderslipRepository.findAll()
	}

	List<Orderslip> filterByPatientType(String type, String filter) {
		return orderslipRepository.filterByPatientType(type, filter)
	}

	List<Orderslip> findByDepartment(String id) {

		if (id) {
			return orderslipRepository.findByDepartment(UUID.fromString(id))

		} else {
			def list = orderslipRepository.findAll().sort { it.createdDate }
			list.reverse(true)
			return list

		}

	}

	Orderslip findById(String id) {
		return orderslipRepository.findById(UUID.fromString(id)).get()
	}

	List<DiagnosticsResultsDto> findByCase(String id) {

		def results = orderSlipItemRepository.findByCase(UUID.fromString(id)).sort { it.created }
		results.reverse(true)

		Set<Department> serviceDepartment = []
		for (def item : results) {
			serviceDepartment.add(item.service.department)
		}

		List<DiagnosticsResultsDto> res = []
		serviceDepartment.each { def dep ->
			DiagnosticsResultsDto diagnostic = new DiagnosticsResultsDto()
			diagnostic.orderNo = results[0].orderslip.orderslipNo
			diagnostic.department = dep
			for (def order : results) {
				if (order.service.department == dep) {

					diagnostic.diagnosticsList.add(order)
				}
			}
			res.add(diagnostic)
		}

		return res
	}

	List<DiagnosticsResultsDto>findByCaseAndDepartment(String id, String departmentId) {
		def results = orderSlipItemRepository.findByCaseAndDepartment(UUID.fromString(id), UUID.fromString(departmentId))
		List<DiagnosticsResultsDto> res = []
		DiagnosticsResultsDto diagnostic = new DiagnosticsResultsDto()
		diagnostic.department = results[0].service.department
		for (def order : results) {
			diagnostic.diagnosticsList.add(order)
		}
		res.add(diagnostic)
		return res
	}

	List<Orderslip> addOrderslip(List<Orderslip> orderslips) {

		List<Orderslip> res = []
		orderslips.each {
			it ->
				it.orderslipNo = generatorService?.getNextValue(GeneratorType.OrderSlip_NO, { i ->
					StringUtils.leftPad(i.toString(), 6, "0")
				})
				it.status = "NEW"
				it.deleted = false
				res.add(orderslipRepository.save(it))
		}
		return res
	}

	List<OrderSlipItem> insertOrderTransaction(Orderslip orderSlip,List<OrderSlipItem> orderItems){

		List<OrderSlipItem> res = []
		def oSlip
		 	oSlip = orderSlip as Orderslip
					oSlip.orderslipNo = generatorService?.getNextValue(GeneratorType.OrderSlip_NO, { i -> StringUtils.leftPad(i.toString(), 6, "0") })
					oSlip.status = "NEW"
					oSlip.deleted =false
		Orderslip ret = orderslipRepository.save(oSlip)
		def items
		items = orderItems as List<OrderSlipItem>
		items.each{
			it ->
			it.orderslip = ret
			res.add(orderSlipItemRepository.save(it))
		}
		return res
	}


	Orderslip save(Orderslip oSlip) {
		orderslipRepository.save(oSlip)
	}
}
