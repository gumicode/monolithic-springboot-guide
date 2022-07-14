package com.example.monolithic.domain.member.service;

import com.example.monolithic.domain.member.dto.MemberGetRequest;
import com.example.monolithic.domain.member.dto.MemberPostRequest;
import com.example.monolithic.domain.member.entity.Member;
import com.example.monolithic.domain.member.repository.MemberQueryRepository;
import com.example.monolithic.domain.member.repository.MemberRepository;
import com.example.monolithic.global.event.AlertPublishEvent;
import com.example.monolithic.global.exception.DomainNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final MemberQueryRepository memberQueryRepository;
	private final ApplicationEventPublisher applicationEventPublisher;

	@Transactional
	public Member save(@NonNull final MemberPostRequest memberPostRequest) {

		Member member = Member.builder()
				.username(memberPostRequest.getUsername())
				.password(memberPostRequest.getPassword())
				.build();
		member.validation();
		Member saved = memberRepository.save(member);

		// AlertPublishEvent 는 global 에 위치시켜 여러 도메인에서 호출할 수 있게 한다.
		applicationEventPublisher.publishEvent(new AlertPublishEvent(saved.getId(), "ALERT_REGISTER_MEMBER"));
		return saved;
	}

	@Transactional(readOnly = true)
	public Member find(Long memberId) {
		return this.memberRepository.findById(memberId)
				.orElseThrow(() -> new DomainNotFoundException(memberId));
	}

	@Transactional(readOnly = true)
	public Page<Member> page(@NonNull final Pageable pageable, final MemberGetRequest memberGetRequest) {
		return this.memberQueryRepository.page(pageable, memberGetRequest);
	}
}
