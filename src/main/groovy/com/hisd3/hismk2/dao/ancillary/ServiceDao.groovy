package com.hisd3.hismk2.dao.ancillary


import com.hisd3.hismk2.repository.ancillary.ServiceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.hisd3.hismk2.domain.ancillary.Service as HisService

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
@Transactional
class ServiceDao {
	
	@Autowired
	private ServiceRepository servicesRepository
	
	@PersistenceContext
	EntityManager entityManager
	
	Set<HisService> findAll() {
		return servicesRepository.findAll()
	}

	HisService findById(String id) {
		return servicesRepository.findById(Long.parseLong(id)).get()
	}

	List<HisService>searchHisServices(String filter) {
		return servicesRepository.searchlist(filter)
	}

	HisService save(com.hisd3.hismk2.domain.ancillary.Service serviceitem) {
		servicesRepository.save(serviceitem)
	}
}
