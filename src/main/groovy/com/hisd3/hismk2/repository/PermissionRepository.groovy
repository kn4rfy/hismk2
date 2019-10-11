package com.hisd3.hismk2.repository

import com.hisd3.hismk2.domain.Permission
import org.springframework.data.jpa.repository.JpaRepository

interface PermissionRepository extends JpaRepository<Permission, UUID> {
	
}