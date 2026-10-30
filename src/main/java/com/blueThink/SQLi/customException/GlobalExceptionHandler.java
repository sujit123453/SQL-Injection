package com.blueThink.SQLi.customException;

import com.blueThink.SQLi.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(UserNotFound ex){
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
        return  new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectPassword.class)
        public ResponseEntity<ErrorResponse> IncorrectPasswordException(IncorrectPassword ex){
            ErrorResponse error = new ErrorResponse(
               ex.getMessage(),
               HttpStatus.NOT_FOUND.value()
            );
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

}
