package com.example.monolithic.global.helper;

import com.example.monolithic.global.dto.response.ErrorResponse;
import com.example.monolithic.global.dto.response.FieldErrorResponse;
import com.example.monolithic.global.error.ErrorCodeSupport;
import com.example.monolithic.global.error.GlobalErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ErrorResponseHelper {

    private final MessageSource messageSource;

    public ResponseEntity<ErrorResponse> code(ErrorCodeSupport errorCodeSupport, String... args) {
        String errorMessage = messageSource.getMessage(errorCodeSupport.name(), args, LocaleContextHolder.getLocale());
        return ResponseEntity
                .status(errorCodeSupport.status())
                .body(new ErrorResponse(errorCodeSupport.name(), errorMessage));
    }

    public ResponseEntity<ErrorResponse> bindErrors(Errors errors) {
        List<FieldErrorResponse> details = errors
                .getFieldErrors()
                .stream()
                .map(error -> {
                    String errorMessage = messageSource.getMessage(error, LocaleContextHolder.getLocale());
                    return new FieldErrorResponse(error, errorMessage);
                }).collect(Collectors.toList());

        ErrorCodeSupport errorCodeSupport = GlobalErrorCode.G0001;
        String errorMessage = messageSource.getMessage(errorCodeSupport.name(), null, LocaleContextHolder.getLocale());
        return ResponseEntity.status(errorCodeSupport.status())
                .body(new ErrorResponse(errorCodeSupport.name(), errorMessage, details));
    }
}
