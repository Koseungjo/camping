package com.example.camping.global.exception;

import com.example.camping.global.config.ResponseDTO;
import com.example.camping.global.enums.ResponseCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseDTO handleNoSuchElementException(NoSuchElementException exception) {
        log.error(exception.getMessage());
        Map<String, String> response = toErrorResponse(ResponseCodeEnum.BAD_REQUEST.getCode(), exception.getMessage(), exception.getMessage());
        return ResponseDTO.ok(response);
    }

    @ExceptionHandler(DuplicationEmailException.class)
    public ResponseDTO handleNoSuchElementException(DuplicationEmailException exception) {
        log.error(exception.getMessage());
        Map<String, String> response = toErrorResponse(ResponseCodeEnum.BAD_REQUEST.getCode(), exception.getMessage(), exception.getMessage());
        return ResponseDTO.ok(response);
    }

    private Map<String,String> toErrorResponse(String code, String message, String exception){
        Map<String,String> response = new HashMap<>();
        response.put("code", code);
        response.put("message", message);
        response.put("error", exception);
        return response;
    }
}
