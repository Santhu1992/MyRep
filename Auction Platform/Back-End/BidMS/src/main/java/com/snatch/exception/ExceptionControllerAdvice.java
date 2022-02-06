package com.snatch.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class.getSimpleName());

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> exceptionHandler(Exception e){
        logger.error(Arrays.stream(e.getStackTrace()).map(a->a.toString()).collect(Collectors.joining(", ")));
        e.printStackTrace();
        logger.error(e.getMessage());
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ItemException.class)
    public ResponseEntity<ErrorMessage> exceptionHandler(ItemException e){
        logger.error(String.valueOf(e.getStackTrace()[0]));
        logger.error(e.getMessage());
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
