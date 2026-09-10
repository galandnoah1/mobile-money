package com.galandnoah.mobile_money.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private String message;
    private String error;
    private int code;
    private List<ValidationError> validationErrors;


    @Getter
    @Setter
    public static class ValidationError{
        private String field;
        private String message;

        public ValidationError(String field, String message)
        {
            this.field = field;
            this.message = message;
        }
    }

    public ApiError(String message, String error, int code)
    {
        this.message = message;
        this.error = error;
        this.code = code;
        this.validationErrors = new ArrayList<>();
    }
}
