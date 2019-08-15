package com.hisd3.hismk2.config

import com.hisd3.hismk2.security.SecurePasswordEncoder
import com.hisd3.hismk2.security.UserDetailsService
import com.hisd3.hismk2.security.handlers.CustomAccessDeniedHandler
import com.hisd3.hismk2.security.handlers.CustomLogoutSuccessHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
class MultiHttpSecurityConfig {
	
	/*
	@Configuration
	class JWTWebSecurityConfigurerAdapter extends  WebSecurityConfigurerAdapter{

			@Autowired
			UserDetailsService userDetailsService

			@Bean
			PasswordEncoder passwordEncoder()  {
					return new SecurePasswordEncoder()
			}

			@Bean
			AccessDeniedHandler accessDeniedHandler(){
					return new CustomAccessDeniedHandler()
			}


			@Override
			protected void configure(AuthenticationManagerBuilder auth) throws Exception {
					auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
			}

			@Override
			protected void configure(HttpSecurity http) throws Exception {


					http.cors().and().csrf().disable()
									.authorizeRequests()

									.antMatchers( "/graphql/**").authenticated()
									.antMatchers( "/api/**").authenticated()
									.antMatchers( "/ping").permitAll()
									.and()
									.exceptionHandling()
									.accessDeniedHandler(accessDeniedHandler())
									.and()
									.addFilter(new JWTAuthenticationFilter(authenticationManager()))
									.addFilter(new JWTAuthorizationFilter(authenticationManager()))

					// this disables session creation on Spring Security
									.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

			}
	}
	*/
	
	@Configuration
	class FormWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
		
		@Autowired
		UserDetailsService userDetailsService
		
		@Bean
		PasswordEncoder passwordEncoder() {
			return new SecurePasswordEncoder()
		}
		
		@Bean
		SimpleUrlAuthenticationSuccessHandler simpleUrlAuthenticationSuccessHandler() {
			return new SimpleUrlAuthenticationSuccessHandler()
		}
		
		@Bean
		SimpleUrlAuthenticationFailureHandler simpleUrlAuthenticationFailureHandler() {
			return new SimpleUrlAuthenticationFailureHandler()
		}
		
		@Bean
		AccessDeniedHandler accessDeniedHandler() {
			return new CustomAccessDeniedHandler()
		}
		
		@Bean
		CustomLogoutSuccessHandler logoutSuccessHandler() {
			return new CustomLogoutSuccessHandler()
		}
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			http.cors()
					.and()
					.csrf()
					.disable()
					.exceptionHandling()
					.accessDeniedHandler(accessDeniedHandler())
					.and()
					.formLogin()
					.loginProcessingUrl("/api/authenticate")
					.successHandler(simpleUrlAuthenticationSuccessHandler())
					.failureHandler(simpleUrlAuthenticationFailureHandler())
					.usernameParameter("username")
					.passwordParameter("password")
					.defaultSuccessUrl("/graphiql")
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
					.antMatchers("/graphql/**").permitAll()
					.antMatchers("/graphiql/**").permitAll()
					.antMatchers("/api/**").permitAll()
					.antMatchers("/ping").permitAll()
					.antMatchers("/public/**").permitAll()
					.antMatchers("/").permitAll()
		}
	}
}
