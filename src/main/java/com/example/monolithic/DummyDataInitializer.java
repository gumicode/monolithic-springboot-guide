package com.example.monolithic;

import com.example.monolithic.domain.member.entity.Member;
import com.example.monolithic.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class DummyDataInitializer implements ApplicationRunner {

	private final MemberRepository memberRepository;

	@Override
	public void run(ApplicationArguments args) {
		initialMember();
	}


	@Transactional
	public void initialMember() {
		// given
		List<Member> members = Stream.iterate(0, n -> n + 1)
				.limit(100)
				.map(t -> Member.builder()
						.username("username" + t)
						.password("abcd1234!")
						.build()).toList();

		// when
		memberRepository.saveAll(members);
	}
}
