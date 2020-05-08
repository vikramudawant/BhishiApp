package com.bhishiapp.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select username as principal, password as credentials, true from user where username=?")
		.authoritiesByUsernameQuery("select username as principal, role as role from user where username=?")
		.passwordEncoder(passwordEncoder()).rolePrefix("ROLE_");
	}

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests().antMatchers("/login", "/about", "/css/**", "/webjars/**").permitAll()
			.antMatchers("/", "/createBhishi", "/bhishiList", "/addMember", "/memberList", "/assignRank", 
					"/addInstallment", "/installmentList", "/paybleList").hasRole("ADMIN")
			.antMatchers("/userProfile").hasRole("USER")
			.and().formLogin().loginPage("/login").defaultSuccessUrl("/default").permitAll()
			.and().logout().logoutSuccessUrl("/login");
	}
	
	

}
