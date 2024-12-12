package com.arjun.EmployeeManager.EmployeeManager.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private boolean status;
    private String message;
    private T response;

    public Response(boolean status, String message, T response) {
        this.status = status;
        this.message = message;
        this.response = response;
    }
}