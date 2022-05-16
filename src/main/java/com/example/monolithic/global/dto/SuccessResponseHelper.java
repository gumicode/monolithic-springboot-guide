package com.example.monolithic.global.dto;

public class SuccessResponseHelper {

    public static <D> SuccessResponse<D> success(final D data) {
        return new SuccessResponse<>(data);
    }
}
