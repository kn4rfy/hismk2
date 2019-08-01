package com.hisd3.hismk2.dao.pms

import com.hisd3.hismk2.domain.pms.VitalSign
import com.hisd3.hismk2.repository.pms.VitalSignRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class VitalSignDao {
	
	@Autowired
	private VitalSignRepository vitalSignRepository
	
	List<VitalSign> getVitalSigns() {
		return vitalSignRepository.getVitalSigns()
	}
}
