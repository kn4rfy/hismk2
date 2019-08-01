package com.hisd3.hismk2.repository

import com.hisd3.hismk2.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param

interface UserRepository extends JpaRepository<User, UUID> {
	
	User findOneByLogin(@Param("login") String login)
	
}