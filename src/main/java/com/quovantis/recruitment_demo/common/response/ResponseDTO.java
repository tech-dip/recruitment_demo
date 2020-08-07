package com.quovantis.recruitment_demo.common.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ResponseDTO <T> {
    private int status;
    private boolean success;
    private  T data;
    private LocalDate timeStamp = LocalDate.now();
    private String message;

    public ResponseDTO (int status ,boolean success,T data, String message) {
        this.status =status;
        this.success = success;
        this.data = data;
        this.message = message;
    }




}
