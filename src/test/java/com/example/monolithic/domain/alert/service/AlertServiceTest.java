package com.example.monolithic.domain.alert.service;

import com.example.monolithic.domain.alert.dto.AlertPostRequest;
import com.example.monolithic.domain.alert.entity.Alert;
import com.example.monolithic.domain.alert.repository.AlertRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AlertServiceTest {

	@Mock
	AlertRepository alertRepository;

	AlertService alertService;

	@BeforeEach
	void init() {
		alertService = new AlertService(alertRepository);
	}

	private AlertPostRequest generatePostRequest() {
		return AlertPostRequest.builder()
				.memberId(1L)
				.message("ALERT_REGISTER_MEMBER")
				.build();
	}

	private Alert generateMockEntity(AlertPostRequest request) {
		return Alert.builder()
				.memberId(request.getMemberId())
				.message(request.getMessage())
				.build();
	}

	@Test
	@DisplayName("알림을 생성 합니다.")
	void registerAlert() {

		// given
		AlertPostRequest alertPostRequest = this.generatePostRequest();
		given(alertRepository.save(any())).willReturn(this.generateMockEntity(alertPostRequest));

		// when
		Alert saved = alertService.save(alertPostRequest);

		// then
		assertEquals(saved.getMemberId(), alertPostRequest.getMemberId());
		assertEquals(saved.getMessage(), alertPostRequest.getMessage());
	}
}