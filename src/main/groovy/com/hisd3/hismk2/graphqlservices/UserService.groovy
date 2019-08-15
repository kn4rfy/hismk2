package com.hisd3.hismk2.graphqlservices

import com.hisd3.hismk2.dao.UserDao
import com.hisd3.hismk2.domain.hrm.Employee
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
class UserService {
	
	@Autowired
	UserDao userDao
	
	@GraphQLQuery(name = "account", description = "Get User account by login")
	Employee findOneByLogin(@GraphQLArgument(name = "login") String login) {
		
		return userDao.findOneByLogin(login)
	}
}
