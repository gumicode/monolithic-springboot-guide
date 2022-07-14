package com.example.monolithic.global.exception;

import com.example.monolithic.global.error.ErrorCodeSupport;
import lombok.Getter;

/**
 * BusinessException
 * 프로젝트 내에서 정의한 오류 발생시 상속받는 최상위 객체
 */
@Getter
public abstract class BusinessException extends RuntimeException {

    private final ErrorCodeSupport errorCodeSupport;
    private final String[] args;
    private final String errorMessage;

    public BusinessException(final ErrorCodeSupport errorCodeSupport, final String... args) {
        this.errorCodeSupport = errorCodeSupport;
        this.args = args;
        this.errorMessage = null;
    }

    public BusinessException(final ErrorCodeSupport errorCodeSupport, final String errorMessage, final String... args) {
        this.errorCodeSupport = errorCodeSupport;
        this.args = args;
        this.errorMessage = errorMessage;
    }
}
