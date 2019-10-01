package com.hisd3.hismk2.graphqlservices

import com.hisd3.hismk2.dao.UserDao
import com.hisd3.hismk2.domain.Authority
import com.hisd3.hismk2.domain.PersistentToken
import com.hisd3.hismk2.domain.User
import com.hisd3.hismk2.domain.hrm.Employee
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
	UserDao userDao
	
	@GraphQLQuery(name = "account", description = "Get User account by login")
	Employee findOneByLogin() {
		
		return userDao.findOneByLogin(SecurityUtils.currentLogin())
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
		
		return !userDao.findOneByLogin(login.toLowerCase())
	}
}
