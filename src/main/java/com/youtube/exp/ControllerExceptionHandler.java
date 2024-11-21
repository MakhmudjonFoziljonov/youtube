package com.youtube.exp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({AppBadRequestException.class})
    public ResponseEntity<String> handlerException(AppBadRequestException e) {
        return ResponseEntity.ok(e.getMessage());
    }
}
