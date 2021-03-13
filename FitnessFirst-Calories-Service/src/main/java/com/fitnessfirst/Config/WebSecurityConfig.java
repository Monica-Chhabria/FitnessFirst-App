package com.fitnessfirst.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/*import com.fitnessfirst.filters.JwtRequestFilter;
*/
import io.jsonwebtoken.lang.Collections;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService myUserDetailsService;
	//@Autowired
//	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder passwordEncoder  = new BCryptPasswordEncoder();
		return      passwordEncoder;

	}
	 
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	           .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
	    }
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	   @Bean
       CorsConfigurationSource corsConfigurationSource() {
           final UrlBasedCorsConfigurationSource source = new     UrlBasedCorsConfigurationSource();
           final CorsConfiguration config = new CorsConfiguration();
           config.setAllowCredentials(true);
           config.addAllowedOrigin(CorsConfiguration.ALL);
         //  config.addAllowedHeaders(Collections.singletonList(CorsConfiguration.ALL));
           config.addAllowedHeader("*");

           config.addAllowedMethod("OPTIONS");
           config.addAllowedMethod("HEAD");
           config.addAllowedMethod("GET");
           config.addAllowedMethod("PUT");
           config.addAllowedMethod("POST");
           config.addAllowedMethod("DELETE");
           config.addAllowedMethod("PATCH");
           source.registerCorsConfiguration("/**", config);
           return source;
       }
	 /*  @Override
	    public void configure(WebSecurity web) throws Exception {
	        web
	                .ignoring()
	                .antMatchers("/resources/**");
	        web.ignoring().antMatchers("/api/authenticate");
	    }*/
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		//  http.cors().and().
		//System.out.println("inside configure");
		
		httpSecurity.cors().and().csrf().disable()
				.authorizeRequests().antMatchers("/**").permitAll().
						anyRequest().authenticated().and().
						exceptionHandling().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		//httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		//httpSecurity.cors();
	}

}