package com.hisd3.hismk2.security

import org.springframework.security.core.context.SecurityContextHolder

class SecurityUtils {
	
	static String currentLogin() {
		def securityContext = SecurityContextHolder.getContext()
		def authentication = securityContext.authentication
		HISUser springSecurityUser
		String userName = null
		
		if (authentication != null) {
			if (authentication.principal.getClass() == HISUser) {
				springSecurityUser = authentication.principal as HISUser
				userName = springSecurityUser.username
			} else if (authentication.principal.getClass() == String) {
				userName = authentication.principal as String
			}
		}
		
		return userName
	}
}
