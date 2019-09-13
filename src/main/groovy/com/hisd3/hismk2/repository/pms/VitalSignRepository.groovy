package com.hisd3.hismk2.repository.pms

import com.hisd3.hismk2.domain.pms.VitalSign
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface VitalSignRepository extends JpaRepository<VitalSign, UUID> {
	@Query(value = "Select vs from VitalSign vs where vs.parentCase.id = :parentCase")
	List<VitalSign> getVitalSignByCase(@Param("parentCase") UUID parentCase)
}