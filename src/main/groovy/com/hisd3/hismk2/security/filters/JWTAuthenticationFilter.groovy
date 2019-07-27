package com.hisd3.hismk2.security.filters

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.ObjectMapper
import com.hisd3.hismk2.security.HISUser
import com.hisd3.hismk2.security.SecurityConstants
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LoginState{
    public def username=""
    public def password=""
}


class JWTAuthenticationFilter extends  UsernamePasswordAuthenticationFilter {

    AuthenticationManager authenticationManagerParam

    JWTAuthenticationFilter(AuthenticationManager authenticationManagerParam) {
        this.authenticationManagerParam = authenticationManagerParam
    }

    @Override
    Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            def creds = new ObjectMapper()
                    .readValue(request?.inputStream, LoginState )


            return authenticationManagerParam.authenticate(
                   new  UsernamePasswordAuthenticationToken(
                            creds.username,
                            creds.password,
                            [] as List<GrantedAuthority>)
            )
        } catch (IOException e ) {
            throw new  RuntimeException(e)
        }



    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        def token = JWT.create()
                .withSubject( (authResult?.principal as HISUser).username)
                .withExpiresAt( new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .withArrayClaim("roles",
                (authResult?.principal as HISUser).authorities*.authority as String[])
               .sign( Algorithm.HMAC512(SecurityConstants.SECRET))

        response?.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token)

    }
}
