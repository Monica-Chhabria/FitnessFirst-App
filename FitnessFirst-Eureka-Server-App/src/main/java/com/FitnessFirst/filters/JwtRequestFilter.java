package  com.FitnessFirst.filters;

import  com.FitnessFirst.security.MyUserDetailsService;
import com.FitnessFirst.util.*;
import com.netflix.eventbus.spi.CatchAllSubscriber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

   
   /* @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(request);

        if (!Objects.isNull(token) && jwtTokenProvider.validateToken(token)) {
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            if (!Objects.isNull(auth))
                SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }*/
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
    	try{
//System.out.println(request.getHeaderNames().nextElement());
//Collections.list(request.getHeaderNames()).forEach(item -> System.out.println("header name is " + item));

        final String authorizationHeader = request.getHeader("Authorisation");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }
        /*else
        {
        	  SecurityContextHolder.getContext().setAuthentication(null);
        }*/
//System.out.println("username"+username);
//System.out.println("SecurityContextHolder.getContext().getAuthentication() "+SecurityContextHolder.getContext().getAuthentication());

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            //System.out.println("username+" +username);

            if (jwtUtil.validateToken(jwt, userDetails)) {
              // System.out.println("validatetoken+" +username);

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
              //  System.out.println("validatetoken+" +username);

            
            }
        }
        chain.doFilter(request, response);
    }
        catch(Exception ex)
        {ex.printStackTrace();}
    }

}
