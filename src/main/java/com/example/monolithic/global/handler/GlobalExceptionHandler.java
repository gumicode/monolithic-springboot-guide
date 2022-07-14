package com.example.monolithic.global.handler;

import com.example.monolithic.global.dto.response.ErrorResponse;
import com.example.monolithic.global.dto.response.ErrorResponseHelper;
import com.example.monolithic.global.error.GlobalErrorCode;
import com.example.monolithic.global.exception.BusinessException;
import com.example.monolithic.global.exception.DomainNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ErrorResponseHelper errorResponseHelper;

    @ExceptionHandler(DomainNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDomainNotFoundException(DomainNotFoundException e) {
        log.error("DomainNotFoundException : ", e);
        if (e.getId() != null) {
            log.error("ID not found : {}", e.getId());
        }
        return errorResponseHelper.code(e.getErrorCode());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        log.error("BusinessException : ", e);
        return errorResponseHelper.code(e.getErrorCode());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("HttpMessageNotReadableException :", e);
        return errorResponseHelper.code(GlobalErrorCode.G0005);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(BindException e) {
        log.error("handleBindException : ", e);
        return errorResponseHelper.bindErrors(e);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException : ", e);
        return errorResponseHelper.code(GlobalErrorCode.G0004);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoHandlerFoundException() {
        return errorResponseHelper.code(GlobalErrorCode.G0002); // 404 의 경우 log 생략
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("handleException : ", e);
        return errorResponseHelper.code(GlobalErrorCode.G0000);
    }
}
