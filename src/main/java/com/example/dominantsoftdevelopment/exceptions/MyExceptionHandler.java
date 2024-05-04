package com.example.dominantsoftdevelopment.exceptions;

import com.example.dominantsoftdevelopment.dto.ApiResult;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedList;
import java.util.List;

@RestControllerAdvice
public class MyExceptionHandler {


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public HttpEntity<ApiResult<ErrorData>> handle(MethodArgumentNotValidException ex) {
        List<ErrorData> errors = new LinkedList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.add(
                    new ErrorData(400,
                            fieldError.getDefaultMessage(),
                            fieldError.getField()));
        }
        return ResponseEntity.status(400).body(ApiResult.errorResponse(errors));
    }

    @ExceptionHandler(value = RestException.class)
    public HttpEntity<ApiResult<ErrorData>> handle(RestException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(ApiResult.errorResponse(ex.getStatus().value(), ex.getMessage()));
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public HttpEntity<ApiResult<ErrorData>> handle(DataIntegrityViolationException ex) {
        List<ErrorData> errors = new LinkedList<>();
            errors.add(new ErrorData(409,ex.getMessage()));
        return ResponseEntity.status(409).body(ApiResult.errorResponse(errors));
    }


}
