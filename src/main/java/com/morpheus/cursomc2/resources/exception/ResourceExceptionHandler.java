package com.morpheus.cursomc2.resources.exception;

import com.morpheus.cursomc2.services.exceptions.DataIntegrityException;
import com.morpheus.cursomc2.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StadardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){

        StadardError err = new StadardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StadardError> objectNotFound(DataIntegrityException e, HttpServletRequest request){

        StadardError err = new StadardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StadardError> validation(MethodArgumentNotValidException e, HttpServletRequest request){

        ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Error de validação", System.currentTimeMillis());
        for (FieldError x :
                e.getBindingResult().getFieldErrors()) {
            err.addError(x.getField(), x.getDefaultMessage());
        }
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
