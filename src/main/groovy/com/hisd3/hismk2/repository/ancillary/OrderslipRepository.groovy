package com.hisd3.hismk2.repository.ancillary


import com.hisd3.hismk2.domain.ancillary.Orderslip
import com.hisd3.hismk2.domain.pms.Patient
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface OrderslipRepository extends JpaRepository<Orderslip, UUID> {

}