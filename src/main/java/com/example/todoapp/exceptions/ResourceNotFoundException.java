//package com.example.todoapp.exceptions;
//
//import lombok.Data;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ResponseStatus;
//@Data
//@ResponseStatus(HttpStatus.NOT_FOUND)
//public class ResourceNotFoundException extends RuntimeException{
//    private static final long serialVersionUID = 1L;
//    private String resourceName;
//    private String fieldName;
//    private Long fieldValue;
//
//    public ResourceNotFoundException(String message, String resourceName, String fieldName, Long fieldValue) {
//        super(String.format("%s Not found with %s : '%s'", resourceName, fieldName, fieldValue));
//        this.resourceName = resourceName;
//        this.fieldName = fieldName;
//        this.fieldValue = fieldValue;
//    }
//
//
//}
