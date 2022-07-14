package com.example.monolithic.global.helper;

import com.example.monolithic.global.dto.response.SuccessResponse;

public class SuccessResponseHelper {

    public static <D> SuccessResponse<D> success(final D data) {
        return new SuccessResponse<>(data);
    }
}
