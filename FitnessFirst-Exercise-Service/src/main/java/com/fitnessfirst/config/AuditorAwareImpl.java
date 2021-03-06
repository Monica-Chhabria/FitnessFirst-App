package com.fitnessfirst.config;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.security.Principal;
//import com.fitnessfirst.model.UserDetail;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    /*@Override
    public Optional<String> getCurrentAuditor(Principal p){
        return Optional.of("Admin");
    
    
    }*/
	
	 public Optional<String> getCurrentAuditor() {
  /*       if (SecurityContextHolder.getContext().getAuthentication() != null) {
             OAuth2Authentication auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
             Object principal = auth.getUserAuthentication().getPrincipal();
             CustomUserDetails userDetails = (CustomUserDetails) principal;
             return Optional.of(userDetails.getUsername());
         } else {
             return Optional.of("Unknown");
         }*/
     
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	        if (authentication == null ||
	                !authentication.isAuthenticated() ||
	                authentication instanceof AnonymousAuthenticationToken) {
	            return Optional.empty();
	        }

	        User userPrincipal = (User) authentication.getPrincipal();
	        
	        return Optional.ofNullable(userPrincipal.getUsername());
	 }
}