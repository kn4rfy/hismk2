package com.hisd3.hismk2.security.filters

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.hisd3.hismk2.security.SecurityConstants
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager)
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        def header = request?.getHeader(SecurityConstants.HEADER_STRING)

        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain?.doFilter(request, response)
            return
        }

        try {
            def authentication = getAuthentication(request)
            SecurityContextHolder.getContext().authentication = authentication
        }
        catch (Exception e) {
            // response.sendError(403,e.message)
            //  throw  RuntimeException(e)
        }
        chain.doFilter(request, response)
    }


    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        def token = request.getHeader(SecurityConstants.HEADER_STRING)
        if (token != null) {
            // parse the token.

            def parsed = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET))
                    .build()
                    .verify(token.replace(SecurityConstants.TOKEN_PREFIX, ""))


            def user = parsed.subject
            def authorities = parsed.getClaim("roles")
            def granted = (authorities as String[]).collect {
                new SimpleGrantedAuthority(it)
            }


            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, granted)
            } else return null

        }


        return null
    }
}
