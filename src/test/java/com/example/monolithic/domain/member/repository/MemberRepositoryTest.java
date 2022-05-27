package com.example.monolithic.domain.member.repository;

import com.example.monolithic.domain.member.dto.MemberGetRequest;
import com.example.monolithic.domain.member.entity.Member;
import com.example.monolithic.global.configuration.TestJPAQueryFactoryConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@EnableJpaAuditing
@Import(TestJPAQueryFactoryConfiguration.class)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원을 생성 합니다. [조건] 저장 수 : 1개 ")
    void saveMember() {

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
    void saveMembers() {

        // given
        List<Member> members = Stream.of(0, 10).map(t -> Member.builder()
                .username("username" + t)
                .password("abcd1234!")
                .build()).toList();

        // when
        List<Member> savedMember = memberRepository.saveAll(members);

        // then
        assertEquals(savedMember.size(), 10);
    }

    @Test
    @DisplayName("회원 목록을 조회 합니다. [조건] 10개 회원 목록을 조회 ")
    void query() {

        //given
        List<Member> members = Stream.of(0, 10).map(t -> Member.builder()
                .username("username" + t)
                .password("abcd1234!")
                .build()).toList();
        Pageable pageable = PageRequest.of(0, 10);
        MemberGetRequest memberGetRequest = MemberGetRequest.builder()
                .build();

        // when
        Page<Member> page = memberRepository.page(pageable, memberGetRequest);

        // then
        assertEquals(page.getSize(), 10);
    }
}