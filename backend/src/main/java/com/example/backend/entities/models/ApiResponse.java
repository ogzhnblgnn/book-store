package com.example.backend.entities.models;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiResponse<T> {

    private HttpStatus status;
    private int statusCode;
    private String message;
    private Timestamp timestamp;
    private T data;

    public ApiResponse() {
        super();
        this.setStatus(HttpStatus.OK);
        this.setStatusCode(HttpStatus.OK.value()); 
        this.setMessage(ResponseMessage.success);
        this.setTimestamp(ResponseMessage.timestamp);
    }

    public ApiResponse(T data){
        this();
        this.setData(data);
    }

    public static <T> ApiResponse<T> default_OK(T data){
        ApiResponse<T> response = new ApiResponse<>();
        return response;
    }

    public static <T> ApiResponse<T> default_ACCEPTED(T data){
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatus(HttpStatus.ACCEPTED);
        response.setStatusCode(HttpStatus.ACCEPTED.value());
        return response;
    }

    public static <T> ApiResponse<T> default_CREATED(T data){
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatus(HttpStatus.CREATED);
        response.setStatusCode(HttpStatus.CREATED.value());
        return response;
    }

    public static <T> ApiResponse<T> default_NO_CONTENT(T data){
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatus(HttpStatus.NO_CONTENT);
        response.setStatusCode(HttpStatus.NO_CONTENT.value());
        return response;
    }
    


    
}
