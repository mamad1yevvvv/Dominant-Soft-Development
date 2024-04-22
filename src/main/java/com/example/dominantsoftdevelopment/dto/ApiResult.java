package com.example.dominantsoftdevelopment.dto;

import com.example.dominantsoftdevelopment.exceptions.ErrorData;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult<T> {

    private boolean success;//false

    private String message;//success : true

    private T data;

    private List<ErrorData> errors;

    private ApiResult(T data) {
        this.success = true;
        this.data = data;
    }

    private ApiResult(String message) {
        this.success = true;
        this.message = message;
    }

    private ApiResult(List<ErrorData> errors) {
        this.errors = errors;
    }


    public static ApiResult<?> successResponse(String message) {
        return new ApiResult<>(message);
    }

    public static <E> ApiResult<E> successResponse(E e) {
        return new ApiResult<>(e);
    }

    public static ApiResult<ErrorData> errorResponse(List<ErrorData> errors) {
        return new ApiResult<>(errors);
    }

    public static ApiResult<ErrorData> errorResponse(int status, String msg) {
        return new ApiResult<>(List.of(new ErrorData(status, msg)));
    }
}
