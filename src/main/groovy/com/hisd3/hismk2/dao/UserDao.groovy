package com.hisd3.hismk2.dao

import com.hisd3.hismk2.domain.Authority
import com.hisd3.hismk2.domain.PersistentToken
import com.hisd3.hismk2.domain.User
import com.hisd3.hismk2.domain.hrm.Employee
import com.hisd3.hismk2.repository.UserRepository
import com.hisd3.hismk2.repository.hrm.EmployeeRepository
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
	
	@Autowired
	private EmployeeRepository employeeRepository
	
	@PersistenceContext
	EntityManager entityManager
	
	List<User> findAll() {
		return userRepository.findAll()
	}
	
	User findById(String id) {
		return userRepository.findById(Long.parseLong(id)).get()
	}
	
	User save(User user) {
		userRepository.save(user)
	}
	
	Employee findOneByLogin(String login) {
		User user = userRepository.findOneByLogin(login)
		
		return employeeRepository.findOneByUser(user)
	}
	
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
