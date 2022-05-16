package com.example.monolithic.global.exception;

import com.example.monolithic.global.error.GlobalErrorCode;
import lombok.Getter;

/**
 * 도메인 검색시 찾을수 없을 때 발생
 */
@Getter
public class DomainNotFoundException extends BusinessException {

    private final Long id;

    public DomainNotFoundException() {
        super(GlobalErrorCode.G0004);
        this.id = null;
    }

    public DomainNotFoundException(Long id) {
        super(GlobalErrorCode.G0004);
        this.id = id;
    }
}
