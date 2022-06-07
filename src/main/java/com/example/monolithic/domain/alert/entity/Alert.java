package com.example.monolithic.domain.alert.entity;

import com.example.monolithic.global.entity.BaseEntity;
import com.example.monolithic.global.entity.EntitySupport;
import com.example.monolithic.global.exception.DomainValidationException;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Slf4j
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Alert extends BaseEntity implements EntitySupport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long memberId;

	private String message;

	@Override
	public void validation() {

		if (memberId == null) {
			log.error("memberId : {}", memberId);
			throw new DomainValidationException();
		}

		if (message == null || message.isBlank()) {
			log.error("message : {}", message);
			throw new DomainValidationException();
		}
	}
}
