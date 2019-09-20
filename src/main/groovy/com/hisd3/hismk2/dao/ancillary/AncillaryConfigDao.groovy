package com.hisd3.hismk2.dao.ancillary

import com.hisd3.hismk2.domain.ancillary.AncillaryConfig
import com.hisd3.hismk2.repository.ancillary.AncillaryConfigRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AncillaryConfigDao {
	@Autowired
	AncillaryConfigRepository ancillaryConfigRepository
	
	List<AncillaryConfig> getConfigByName(String name) {
		return ancillaryConfigRepository.getConfigByEntityName(name)
	}
	
}
