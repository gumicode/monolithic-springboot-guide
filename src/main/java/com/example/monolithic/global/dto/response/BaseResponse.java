package com.example.monolithic.global.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseResponse {

	protected LocalDateTime createDatetime;
	protected LocalDateTime updateDatetime;
}
