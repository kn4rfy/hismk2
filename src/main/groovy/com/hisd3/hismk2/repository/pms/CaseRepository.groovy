package com.hisd3.hismk2.repository.pms

import com.hisd3.hismk2.domain.pms.Case
import org.springframework.data.jpa.repository.JpaRepository

interface CaseRepository extends JpaRepository<Case, UUID> {

}