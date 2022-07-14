package com.example.monolithic.domain.alert.repository;

import com.example.monolithic.domain.alert.entity.Alert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@EnableJpaAuditing
class AlertRepositoryTest {

	@Autowired
	AlertRepository alertRepository;

	private Alert generateEntity() {
		return Alert.builder()
				.memberId(1L)
				.message("ALERT_REGISTER_MEMBER")
				.build();
	}

	@Test
	@DisplayName("알림을 생성 합니다. [조건] 저장 수 : 1개 ")
	void save() {

		// given
		Alert alert = this.generateEntity();

		// when
		Alert savedAlert = alertRepository.save(alert);

		// then
		assertEquals(savedAlert.getMemberId(), alert.getMemberId());
	}

}