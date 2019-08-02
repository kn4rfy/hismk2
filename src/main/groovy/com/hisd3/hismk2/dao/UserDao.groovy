package com.hisd3.hismk2.dao

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
	
	List<User> findAll() {
		return userRepository.findAll()
	}
	
	User findById(String id) {
		return userRepository.findById(UUID.fromString(id)).get()
	}
}
