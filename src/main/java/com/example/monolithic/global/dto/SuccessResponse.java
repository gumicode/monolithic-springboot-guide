package com.example.monolithic.global.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessResponse<T> {

    private boolean success = true;
    private T data;

    public SuccessResponse(final T data) {
        this.data = data;
    }
}
