package com.policymanagament.policyplan.exception;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        logger.error("Exception: ", e);
        return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        
        
    }
    public class PolicyLimitReachedException extends Exception {
        public PolicyLimitReachedException(String message) {
            super(message);
        }
    }

}