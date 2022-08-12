package com.example.backend.exceptions;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.backend.entities.models.ErrorResponse;
import com.example.backend.entities.models.ResponseMessage;
import com.example.backend.exceptions.notFoundExceptions.NotFoundExcepiton;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(NotFoundExcepiton.class)
    public ResponseEntity<?> handleNotFoundExceptions(NotFoundExcepiton ex, WebRequest request){
        var response = new ErrorResponse<>();
        response.setStatus(HttpStatus.NOT_FOUND);
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        response.setTimestamp(ResponseMessage.timestamp);
        response.setPath(request.getDescription(false));
        response.setErrors(Arrays.asList(ex.getMessage()));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
            var response = new ErrorResponse<>();
            response.setPath(request.getDescription(false));
            response.setErrors(Arrays.asList(ex.getMessage(), "Required Type: " + ex.getRequiredType()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Path Variable is Missing");
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
                List<String> errors = new ArrayList<>();

                errors.add(String.format("Number of errors : %s", ex.getErrorCount()));

                for (FieldError err : ex.getFieldErrors()) {
                        errors.add(err.getField() + " : " + err.getDefaultMessage() + " Rejected Value: "
                                        + err.getRejectedValue());

                }

                var response = new ErrorResponse<>();
                response.setPath(request.getDescription(false));
                response.setMessage("MethodArgumentNotValid");
                response.setErrors(errors);


                return ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .body(response);
    }
}
