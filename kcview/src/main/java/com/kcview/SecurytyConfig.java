package com.kcview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurytyConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public void globalConfig(AuthenticationManagerBuilder auth) throws Exception {
		//a voir pour authentification bdd
		//auth.inMemoryAuthentication()
			//.withUser("admin").password("123").roles("ADMIN");
		
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().anyRequest().permitAll();
		}

}
