package com.example.monolithic.domain.member.repository;

import com.example.monolithic.domain.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@EnableJpaAuditing
class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;

	@Test
	@DisplayName("회원을 생성 합니다. [조건] 저장 수 : 1개 ")
	void save() {

		// given
		String username = "username1";
		Member member = Member.builder()
				.username(username)
				.password("abcd1234!")
				.build();

		// when
		Member savedMember = memberRepository.save(member);

		// then
		assertEquals(savedMember.getUsername(), username);
	}

	@Test
	@DisplayName("회원을 생성 합니다. [조건] 저장 수 : 10개 ")
	void saveAll() {

		// given
		List<Member> members = Stream.iterate(0, n -> n + 1)
				.limit(10)
				.map(t -> Member.builder()
						.username("username" + t)
						.password("abcd1234!")
						.build()).toList();

		// when
		List<Member> savedMember = memberRepository.saveAll(members);

		// then
		assertEquals(savedMember.size(), 10);
	}

	@Test
	@DisplayName("회원을 삭제 합니다. [조건] 삭제 수 : 1개 ")
	void delete() {

		// given
		String username = "username1";
		Member member = Member.builder()
				.username(username)
				.password("abcd1234!")
				.build();
		Member saveMember = memberRepository.save(member);

		// when
		memberRepository.delete(saveMember);

		// then
		Optional<Member> findMember = memberRepository.findById(saveMember.getId());
		assertTrue(findMember.isEmpty());
	}

	@Test
	@DisplayName("회원을 수정 합니다. [조건] username 을 수정 합니다. ")
	void update() {

		// given
		String username = "username1";
		String updateUsername = "username2";
		Member member = Member.builder()
				.username(username)
				.password("abcd1234!")
				.build();
		Member saveMember = memberRepository.save(member);
		Member findMember = memberRepository.findById(saveMember.getId())
				.orElseThrow();

		// when
		findMember.setUsername(updateUsername);
		memberRepository.save(findMember);

		// then
		Member updateMember = memberRepository.findById(saveMember.getId())
				.orElseThrow();
		assertEquals(updateMember.getUsername(), updateUsername);
	}
}