package com.hisd3.hismk2.repository.ancillary

import com.hisd3.hismk2.domain.ancillary.Services
import org.springframework.data.jpa.repository.JpaRepository

interface ServicesRepository extends JpaRepository<Services, UUID> {

}