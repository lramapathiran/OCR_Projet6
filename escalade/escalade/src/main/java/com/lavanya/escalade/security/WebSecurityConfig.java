package com.lavanya.escalade.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Configuration to overrides WebSecurityConfigurerAdapter for spring security.
 * @author lavanya
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String ADMIN = "ADMIN";
	private static final String USER = "USER";

	@Autowired
	private UserDetailsService userDetailsService;
	
	/**
	 * Method that add authentication based upon the custom UserDetailsService that is passed in.
	 * It then returns a DaoAuthenticationConfigurer to allow customization of the authentication.
	 * @param auth, create an AuthenticationManager. Allows for easily building in memory authentication,
	 * adding UserDetailsService and adding AuthenticationProvider's.
	 * @throws Exception which checks for all exceptions.
	*/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	
	/**
	* Override the method of the same name in WebSecurityConfigurerAdapter to configure the HttpSecurity.
	* @param http, HttpSecurity
	* @throws Exception which checks for all exceptions.
	*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests().antMatchers("/admin").hasRole(ADMIN)
			.antMatchers("/user").hasAnyAuthority(ADMIN, USER)
			.antMatchers("/all").permitAll()
			.antMatchers("/assets/**").permitAll()
			.antMatchers("/vendor/**").permitAll()
			.antMatchers("/css/**").permitAll()
			.antMatchers("/img/**").permitAll()
			.antMatchers("/static/**").permitAll()
			.antMatchers("/js/**").permitAll()
			.antMatchers("/users").authenticated()
			.antMatchers("/createSite").authenticated()
			.antMatchers("/user/sites").authenticated()
			.antMatchers("/sites").authenticated()
			.antMatchers("/site/{id}/updateComment/{commentId}").authenticated()
			.antMatchers("/createTopo/{siteId}").authenticated()			
			.antMatchers("/user/topos").authenticated()
			.antMatchers("/topos").authenticated()
			.antMatchers("/topo/{id}").authenticated()
			.antMatchers("/showTopos").authenticated()
			.and().formLogin()
			.loginPage("/login")
			.permitAll()
			.defaultSuccessUrl("/user")
			.failureUrl("/login?error=true")
			.and().logout()
			.permitAll()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login?logout=true")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID");
	}
	
	/**
	* Service interface for encoding passwords.The preferred implementation is BCryptPasswordEncoder.
	* @return an instance of BCryptPasswordEncoder.
	*/
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
