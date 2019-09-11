package com.hisd3.hismk2.repository.pms

import com.hisd3.hismk2.domain.pms.Administration
import org.springframework.data.jpa.repository.JpaRepository

interface AdministrationRepository extends JpaRepository<Administration, UUID> {

}