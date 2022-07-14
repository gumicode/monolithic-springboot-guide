package com.example.monolithic.global.error;

import org.springframework.http.HttpStatus;

public interface ErrorCodeSupport {
    HttpStatus status();
    String name();
}
