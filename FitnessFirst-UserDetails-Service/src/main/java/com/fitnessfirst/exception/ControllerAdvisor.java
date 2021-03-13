package com.fitnessfirst.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor  extends ResponseEntityExceptionHandler  {
	@ExceptionHandler(UserNotFoundException.class)

    public ResponseEntity<Object> handleUserNotFoundException(
    		UserNotFoundException ex, WebRequest request) {


        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Incorrect UserName or Passwords");
       // System.out.println("inside  handleUserNotFoundException");
        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }

}
