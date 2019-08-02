package com.hisd3.hismk2.repository.pms

import com.hisd3.hismk2.domain.pms.VitalSign
import org.springframework.data.jpa.repository.JpaRepository

interface VitalSignRepository extends JpaRepository<VitalSign, UUID> {

}