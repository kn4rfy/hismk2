package com.hisd3.hismk2.security.handlers

import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.sendError(401, accessDeniedException.message)
	}
}
