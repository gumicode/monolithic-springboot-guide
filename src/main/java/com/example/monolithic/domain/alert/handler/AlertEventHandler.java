package com.example.monolithic.domain.alert.handler;

import com.example.monolithic.domain.alert.converter.AlertConverter;
import com.example.monolithic.global.event.AlertPublishEvent;
import com.example.monolithic.domain.alert.service.AlertService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Component
@RequiredArgsConstructor
public class AlertEventHandler {

	private final AlertService alertService;

	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void publishEventListener(@Validated final AlertPublishEvent event) {
		alertService.register(AlertConverter.to(event));
	}
}
