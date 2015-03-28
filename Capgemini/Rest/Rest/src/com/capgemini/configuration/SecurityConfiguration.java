package com.capgemini.configuration;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfiguration {

	@Resource(name = "databaseUserService")
	private UserDetailsService userService;;

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(new Md5PasswordEncoder());
		provider.setUserDetailsService(userService);
		return provider;
	}

	/*
	 * public @Bean(name = "org.springframework.security.authenticationManager")
	 * AuthenticationManager authenticationManager() {
	 * List<AuthenticationProvider> providers = new
	 * ArrayList<AuthenticationProvider>();
	 * providers.add(daoAuthenticationProvider()); return new
	 * ProviderManager(providers); }
	 */

}
