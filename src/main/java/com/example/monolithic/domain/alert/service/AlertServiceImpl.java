package com.example.monolithic.domain.alert.service;

import com.example.monolithic.domain.alert.dto.AlertPostRequest;
import com.example.monolithic.domain.alert.entity.Alert;
import com.example.monolithic.domain.alert.repository.AlertRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlertServiceImpl implements AlertService {

	private final AlertRepository alertRepository;

	@Transactional
	public Alert save(final AlertPostRequest alertPostRequest) {

		Alert alert = Alert.builder()
				.memberId(alertPostRequest.getMemberId())
				.message(alertPostRequest.getMessage())
				.build();
		alert.validation();
		return alertRepository.save(alert);
	}
}
