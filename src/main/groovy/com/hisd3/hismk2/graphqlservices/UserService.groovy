package com.hisd3.hismk2.graphqlservices

import com.hisd3.hismk2.dao.UserDao
import com.hisd3.hismk2.domain.Authority
import com.hisd3.hismk2.domain.PersistentToken
import com.hisd3.hismk2.domain.User
import com.hisd3.hismk2.domain.hrm.Employee
import com.hisd3.hismk2.repository.UserRepository
import com.hisd3.hismk2.repository.hrm.EmployeeRepository
import com.hisd3.hismk2.security.SecurityUtils
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLContext
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@TypeChecked
@Component
@GraphQLApi
class UserService {
	
	@Autowired
	private UserRepository userRepository
	
	@Autowired
	private EmployeeRepository employeeRepository
	
	@Autowired
	UserDao userDao
	
	//============== All Queries ====================
	
	@GraphQLQuery(name = "account", description = "Get User by login")
	Employee findOneByLogin() {
		User user = userRepository.findOneByLogin(SecurityUtils.currentLogin())
		return employeeRepository.findOneByUser(user)
	}
	
	@GraphQLQuery(name = "authorities", description = "Get all User authorities")
	Set<Authority> getAuthorities(@GraphQLContext User user) {
		return userDao.getAuthorities(user)
	}
	
	@GraphQLQuery(name = "persistentTokens", description = "Get all User persistentTokens")
	Set<PersistentToken> getPersistentTokens(@GraphQLContext User user) {
		return userDao.getPersistentTokens(user)
	}
	
	@GraphQLQuery(name = "roles", description = "Get all User roles")
	Set<String> getRoles(@GraphQLContext User user) {
		return userDao.getRoles(user)
	}
	
	@GraphQLQuery(name = "isLoginUnique", description = "Check if username exists")
	Boolean isLoginUnique(@GraphQLArgument(name = "login") String login) {
		return !userRepository.findOneByLogin(login.toLowerCase())
	}
}
