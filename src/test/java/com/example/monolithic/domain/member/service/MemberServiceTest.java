package com.example.monolithic.domain.member.service;

import com.example.monolithic.domain.member.dto.MemberPostRequest;
import com.example.monolithic.domain.member.entity.Member;
import com.example.monolithic.domain.member.repository.MemberRepository;
import com.example.monolithic.global.exception.DomainValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;

    MemberService memberService;

    @BeforeEach
    void setMemberService(){
        memberService = new MemberServiceImpl(memberRepository);
    }

    @Test
    @DisplayName("회원을 생성 합니다.")
    void registerMember() {

        // given
        String username = "username1";
        String password = "abcd1234!";
        MemberPostRequest memberPostRequest = MemberPostRequest.builder()
                .username(username)
                .password(password)
                .build();
        given(memberRepository.save(any())).willReturn(Member.builder()
                .id(1L)
                .username(username)
                .password(password)
                .build());

        // when
        Member registerMember = memberService.registerMember(memberPostRequest);

        // then
        assertEquals(registerMember.getUsername(), username);
    }

    @Test
    @DisplayName("회원을 생성 합니다. [조건] 올바르지 않은 username 정규식을 추가 합니다. [param] username : user")
    void registerMemberFailedUsernameRegex() {

        // given
        String username = "user";
        String password = "abcd1234!";
        MemberPostRequest memberPostRequest = MemberPostRequest.builder()
                .username(username)
                .password(password)
                .build();

        // when
        assertThrows(DomainValidationException.class, () -> {
            memberService.registerMember(memberPostRequest);
        });

        // then
    }

    @Test
    @DisplayName("회원을 생성 합니다. [조건] 올바르지 않은 password 정규식을 추가 합니다. [param] password : 1")
    void registerMemberFailedPasswordRegex() {

        // given
        String username = "username1";
        String password = "1!";
        MemberPostRequest memberPostRequest = MemberPostRequest.builder()
                .username(username)
                .password(password)
                .build();

        // when
        assertThrows(DomainValidationException.class, () -> {
            memberService.registerMember(memberPostRequest);
        });

        // then
    }
}