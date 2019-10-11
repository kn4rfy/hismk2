package com.hisd3.hismk2.repository

import com.hisd3.hismk2.domain.Authority
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorityRepository extends JpaRepository<Authority, UUID> {
	
}