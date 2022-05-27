package com.example.monolithic.domain.member.service;

import com.example.monolithic.domain.member.dto.MemberGetRequest;
import com.example.monolithic.domain.member.dto.MemberPostRequest;
import com.example.monolithic.domain.member.entity.Member;
import com.example.monolithic.domain.member.repository.MemberRepository;
import com.example.monolithic.global.exception.DomainNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member registerMember(@NonNull final MemberPostRequest memberPostRequest) {

        Member member = Member.builder()
                .username(memberPostRequest.getUsername())
                .password(memberPostRequest.getPassword())
                .build();
        member.validation();
        return memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public Member getMember(Long memberId) {
        return this.memberRepository.findById(memberId)
                .orElseThrow(() -> new DomainNotFoundException(memberId));
    }

    @Transactional(readOnly = true)
    public Page<Member> pageMember(@NonNull final Pageable pageable, final MemberGetRequest memberGetRequest) {
        return this.memberRepository.page(pageable, memberGetRequest);
    }
}
