
package com.synex.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

//	@Autowired
//	UserDetailsService userDetailsService;
	

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	@Bean 
	public SecurityFilterChain apiFilterChain2(HttpSecurity http) throws Exception {
		http
			.apply(MyCustomDsl.customDsl())
			.flag(true).and()
			.authorizeRequests()
				.requestMatchers("/", "/home").permitAll().and()
			      .exceptionHandling().accessDeniedPage("/accessDeniedPage").and()
		.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/home").permitAll().and()
		.logout()
		.logoutSuccessUrl("/login")
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID")
        .permitAll();
		
		return http.build();
	}
	
//	@Bean 
//	public SecurityFilterChain apiFilterChain2(HttpSecurity http) throws Exception {
//		http
//			.apply(MyCustomDsl.customDsl())
//			.flag(true).and()
//			.authorizeRequests()
//				.requestMatchers("/", "/home","/home").permitAll().and()
//			      .exceptionHandling().accessDeniedPage("/accessDeniedPage").and()
//			.authorizeRequests()
//				.requestMatchers("/userForm", "/submitUser", "/form","/submitForm").hasAnyAuthority("ADMIN").and()
//		.formLogin()
//			.loginPage("/login")
//			.defaultSuccessUrl("/home").permitAll().and()
//		.logout()
//		.logoutSuccessUrl("/")
//        .invalidateHttpSession(true)
//        .deleteCookies("JSESSIONID")
//        .permitAll();
//		
//		return http.build();
//	}
	
//	@Bean
//	public SecurityFilterChain apiFilterChain2(HttpSecurity http) throws Exception {
//	    http
//	        .apply(MyCustomDsl.customDsl())
//	            .flag(true)
//	            .and()
//	        .authorizeHttpRequests(authorizeRequests -> // Using lambda for authorizeHttpRequests
//	            authorizeRequests
//	                .requestMatchers("/", "/home").permitAll()
//	                .anyRequest().authenticated() // It's good practice to explicitly define access for other requests
//	        )
//	        .exceptionHandling(exceptionHandling -> // Using lambda for exceptionHandling
//	            exceptionHandling
//	                .accessDeniedPage("/accessDeniedPage")
//	        )
//	        .formLogin(formLogin -> // Using lambda for formLogin
//	            formLogin
//	                .loginPage("/login")
//	                .defaultSuccessUrl("/home")
//	                .permitAll()
//	        )
//	        .logout(logout -> // Using lambda for logout
//	            logout
//	                .logoutSuccessUrl("/")
//	                .invalidateHttpSession(true)
//	                .deleteCookies("JSESSIONID")
//	                .permitAll()
//	        );
//
//	    return http.build();
//	}

}
