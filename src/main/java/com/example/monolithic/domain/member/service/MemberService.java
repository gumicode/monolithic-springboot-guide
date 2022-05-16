package com.example.monolithic.domain.member.service;

import com.example.monolithic.domain.member.dto.MemberGetRequest;
import com.example.monolithic.domain.member.dto.MemberPostRequest;
import com.example.monolithic.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

public interface MemberService {

    Member registerMember(@NonNull final MemberPostRequest memberPostRequest);

    Member getMember(@NonNull final Long memberId);

    Page<Member> pageMember(@NonNull final Pageable pageable, final MemberGetRequest memberGetRequest);
}
