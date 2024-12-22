package com.dev.snsboard.exception;

import com.dev.snsboard.model.error.ClientErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 개발하고 있는 RestApiController 전역에서 발생하는 예외를 핸들링 할 것
@RestControllerAdvice
public class GlobalExceptionHandler {

                      // 핸들링하고자 하는 예외의 클래스 정보
    @ExceptionHandler(ClientErrorException.class)
    public ResponseEntity<ClientErrorResponse> handleClientErrorException(ClientErrorException e) {
         return new ResponseEntity<>(
                 new ClientErrorResponse(e.getStatus(), e.getMessage()), e.getStatus());
    }
}
