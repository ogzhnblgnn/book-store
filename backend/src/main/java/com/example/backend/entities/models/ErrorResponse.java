package com.example.backend.entities.models;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class ErrorResponse<T> extends ApiResponse<T>{
    private String path;
    private List<String> errors;

    public ErrorResponse(){
        super();
        this.setStatus(HttpStatus.BAD_REQUEST);
        this.setStatusCode(HttpStatus.BAD_REQUEST.value());
        this.setMessage(ResponseMessage.fail);
    }
}
