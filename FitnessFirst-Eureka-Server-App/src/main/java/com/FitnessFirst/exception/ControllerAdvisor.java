package com.FitnessFirst.exception;
//Class for centralised exception handling

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.jsonwebtoken.*;
import io.jsonwebtoken.SignatureException;
@ControllerAdvice
public class ControllerAdvisor  extends ResponseEntityExceptionHandler {
	   @Override
	    protected ResponseEntity<Object> handleMethodArgumentNotValid(
	        MethodArgumentNotValidException ex, HttpHeaders headers, 
	        HttpStatus status, WebRequest request) {

	        Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDate.now());
	        body.put("message", status.value());

	        List<String> errors = ex.getBindingResult()
	                .getFieldErrors()
	                .stream()
	                .map(x -> x.getDefaultMessage())
	                .collect(Collectors.toList());

	        body.put("errors", errors);

	        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	    }
	   //method to handle exception when calling authenticate api and getting invalid username password
	   @ExceptionHandler(InternalAuthenticationServiceException.class)
	    public ResponseEntity<Object> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException e) {
		    Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("message", "Incorrect UserName or Password");

	        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
		   
	   }
		/*   ResponseEntity<String> response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        return response;*/
	    
	    /*@ExceptionHandler(NoDataFoundException.class)
	    public ResponseEntity<Object> handleNodataFoundException(
	        NoDataFoundException ex, WebRequest request) {

	        Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("message", "No cities foun)d";

	        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	    }*/
	    @ExceptionHandler(BadCredentialsException.class)

	    public ResponseEntity<Object> handleBadCredentialsException(
	    		BadCredentialsException ex, WebRequest request) {


	        Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("message", "Incorrect UserName or Passwords");

	        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	    }
	    
	/*    public ResponseEntity<Object> handleServletException(
	    		ServletException ex, WebRequest request) {

		      HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

	        Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("message", "Incorrect UserName or Passwords");

	        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	    }*/
	    @ExceptionHandler(value = { ServletException.class })
	    public ResponseEntity servletException(ServletException e) {
	      String message = e.getMessage();
	      System.out.println("inside servletexception "+message);
	      HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	      if (message.equals("token_expired")) {
	        httpStatus = HttpStatus.UNAUTHORIZED;
	        message = "the token is expired and not valid anymore";
	      }
	    //  RestErrorResponse restErrorResponse = new RestErrorResponse(httpStatus, message);
	      Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("message", "the token is expired and not valid anymore");

	        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);	   
	        }
	    @ExceptionHandler(SignatureException.class)

	    public ResponseEntity<Object> handleSignatureException(
	    		SignatureException ex, WebRequest request) {


	        Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("message", "Invalid JWT Signature");

	        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	    }
	    @ExceptionHandler(ExpiredJwtException.class)

	    public ResponseEntity<Object> handleExpiredJwtException(
	    		ExpiredJwtException  ex, WebRequest request) {

System.out.println("inside expired jwt exception");
	        Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("message", "Expired JWT token");

	        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	    }
	       
	    @ExceptionHandler(UnsupportedJwtException.class)

	    public ResponseEntity<Object> handleUnsupportedJwtException(
	    		UnsupportedJwtException   ex, WebRequest request) {


	        Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("message", "Unsupported JWT exception");

	        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	    }
	    @ExceptionHandler(IllegalArgumentException.class)

	    public ResponseEntity<Object> handleIllegalArgumentException(
	    		IllegalArgumentException    ex, WebRequest request) {


	        Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("message", "Jwt claims string is empty");

	        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	    }
	       
	
}
