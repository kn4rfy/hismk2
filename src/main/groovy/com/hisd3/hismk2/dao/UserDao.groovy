package com.hisd3.hismk2.dao

import com.hisd3.hismk2.domain.Authority
import com.hisd3.hismk2.domain.PersistentToken
import com.hisd3.hismk2.domain.User
import com.hisd3.hismk2.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
@Transactional
class UserDao {
	
	@Autowired
	private UserRepository userRepository
	
	@PersistenceContext
	EntityManager entityManager
	
	Set<Authority> getAuthorities(User user) {
		
		def mergedUser = entityManager.merge(user)
		mergedUser.authorities.size()
		return mergedUser.authorities as Set
	}
	
	Set<PersistentToken> getPersistentTokens(User user) {
		
		def mergedUser = entityManager.merge(user)
		mergedUser.persistentTokens.size()
		return mergedUser.persistentTokens as Set
	}
	
	Set<String> getRoles(User user) {
		
		def mergedUser = entityManager.merge(user)
		mergedUser.roles.size()
		return mergedUser.roles as Set
	}
}
