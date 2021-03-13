package com.fitnessfirst.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fitnessfirst.model.UserDetail;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class MyUserDetailsService implements UserDetailsService {
	   @Autowired
	   RestTemplate restTemplate;
	   @Autowired
		private Environment env;
    public Environment getEnv() {
		return env;
	}
	public void setEnv(Environment env) {
		this.env = env;
	}
	@Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    /*	System.out.println("loaduserbyusername"+s);
    	String userjson = getUserDetails(s); 
    	System.out.println(userjson);*/
    	UserDetail user = getUserDetails(s); 
    	System.out.println(user.getEmail());
    	System.out.println(user.getPassword());

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                new ArrayList<>());
    /*
    	User user = this.userRepository.getUserFromEmail(s) ;
		if(user == null)
		{
			
			throw new UsernameNotFoundException("No such user found");
		}
	//	System.out.println("user role"+user.getRole());
		
		return new CustomUserDetails(user);*/
    
    }
    public UserDetail getUserDetails(String emailid)
    {
    	 HttpHeaders headers = new HttpHeaders();
         headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
         HttpEntity <String> entity = new HttpEntity<String>(headers);
         final String uri =  env.getProperty("userdetailsfetchuser.url");
         return restTemplate.exchange(uri+emailid, HttpMethod.GET, entity, UserDetail.class).getBody();
    	
    	//return "";
    }
}