package com.example.monolithic.domain.alert.converter;

import com.example.monolithic.domain.alert.dto.AlertPostRequest;
import com.example.monolithic.global.event.AlertPublishEvent;

public class AlertConverter {

	/**
	 * alertPublishEvent to AlertPostRequest convert
	 */
	public static AlertPostRequest to(final AlertPublishEvent alertPublishEvent) {
		return AlertPostRequest.builder()
				.memberId(alertPublishEvent.getMemberId())
				.message(alertPublishEvent.getMessage())
				.build();
	}
}
