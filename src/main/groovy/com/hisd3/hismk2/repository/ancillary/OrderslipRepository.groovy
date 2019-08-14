package com.hisd3.hismk2.repository.ancillary

import com.hisd3.hismk2.domain.ancillary.Orderslip
import org.springframework.data.jpa.repository.JpaRepository

interface OrderslipRepository extends JpaRepository<Orderslip, UUID> {

}