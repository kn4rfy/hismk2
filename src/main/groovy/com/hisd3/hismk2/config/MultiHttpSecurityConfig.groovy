package com.hisd3.hismk2.config

import com.hisd3.hismk2.security.SecurePasswordEncoder
import com.hisd3.hismk2.security.UserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
class MultiHttpSecurityConfig {
	
	@Configuration
	class FormWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
		
		@Autowired
		UserDetailsService userDetailsService
		
		@Bean
		PasswordEncoder passwordEncoder() {
			return new SecurePasswordEncoder()
		}
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
		}
		
		private AuthenticationSuccessHandler successHandler() {
			return new AuthenticationSuccessHandler() {
				@Override
				void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
					httpServletResponse.getWriter().append("OK")
					httpServletResponse.setStatus(200)
				}
			}
		}
		
		private AuthenticationFailureHandler failureHandler() {
			return new AuthenticationFailureHandler() {
				@Override
				void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException authentication) throws IOException, ServletException {
					httpServletResponse.getWriter().append("Authentication failure")
					httpServletResponse.setStatus(401)
				}
			}
		}
		
		private AccessDeniedHandler accessDeniedHandler() {
			return new AccessDeniedHandler() {
				@Override
				void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException authentication) throws IOException, ServletException {
					httpServletResponse.getWriter().append("Access denied")
					httpServletResponse.setStatus(403)
				}
			}
		}
		
		private AuthenticationEntryPoint authenticationEntryPoint() {
			return new AuthenticationEntryPoint() {
				@Override
				void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException authentication) throws IOException, ServletException {
					httpServletResponse.getWriter().append("Not authenticated")
					httpServletResponse.setStatus(401)
				}
			}
		}
		
		private LogoutSuccessHandler logoutSuccessHandler() {
			return new LogoutSuccessHandler() {
				@Override
				void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
					httpServletResponse.getWriter().append("Logout success")
					httpServletResponse.setStatus(200)
				}
			}
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			http.cors()
					.and()
					.csrf()
					.disable()
					.exceptionHandling()
					.accessDeniedHandler(accessDeniedHandler())
					.authenticationEntryPoint(authenticationEntryPoint())
					.and()
					.formLogin()
					.loginProcessingUrl("/api/authenticate")
					.successHandler(successHandler())
					.failureHandler(failureHandler())
					.usernameParameter("username")
					.passwordParameter("password")
			//.defaultSuccessUrl("/graphiql")
					.and()
					.httpBasic()
					.and()
					.logout()
					.logoutUrl("/api/logout")
					.logoutSuccessHandler(logoutSuccessHandler())
					.deleteCookies("JSESSIONID")
					.permitAll()
					.and()
					.authorizeRequests()
					.antMatchers("/graphql/**").authenticated()
					.antMatchers("/graphiql/**").authenticated()
					.antMatchers("/api/**").authenticated()
					.antMatchers("/ping").permitAll()
					.antMatchers("/public/**").permitAll()
					.antMatchers("/chat/**").permitAll()
					.antMatchers("/").permitAll()
		}
	}
}
