package com.example.monolithic.domain.member.repository;

import com.example.monolithic.domain.member.dto.request.MemberGetRequest;
import com.example.monolithic.domain.member.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@EnableJpaAuditing
class MemberQueryRepositoryTest {

	@Autowired
	EntityManager entityManager;

	@Autowired
	MemberRepository memberRepository;

	MemberQueryRepository memberQueryRepository;

	@BeforeEach
	public void init() {
		memberQueryRepository = new MemberQueryRepository(new JPAQueryFactory(entityManager));
	}

	@Test
	@DisplayName("회원 목록을 조회 합니다. [조건] 10개 회원 목록을 조회 ")
	void query() {

		//given
		List<Member> members = Stream.iterate(0, n -> n + 1)
				.limit(10)
				.map(t -> Member.builder()
						.username("username" + t)
						.password("abcd1234!")
						.build()).toList();
		memberRepository.saveAll(members);

		Pageable pageable = PageRequest.of(0, 10);
		MemberGetRequest memberGetRequest = MemberGetRequest.builder()
				.build();

		// when
		Page<Member> page = memberQueryRepository.page(pageable, memberGetRequest);

		// then
		assertEquals(page.getContent().size(), 10);
	}
}