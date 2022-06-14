package com.homework.hanghae99homework02.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice //RestController의 예외처리에 대해서 AOP를 적용하기 위해 사용
public class ApiException extends RuntimeException{

    @ExceptionHandler(MethodArgumentNotValidException.class) //예외가 발생한 요청을 처리하기 위해
    public ResponseEntity<Map<String, String>> handleMethodNotValidException(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException e){
        Map<String, String> errors = new HashMap<>();
        errors.put("여기에 뭘써야하죠",e.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { WrongIdException.class })
    protected ResponseEntity<ErrorResponse> handleCustomException(WrongIdException e) {
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }
}