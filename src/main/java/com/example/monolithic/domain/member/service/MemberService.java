package com.example.monolithic.domain.member.service;

import com.example.monolithic.domain.member.dto.MemberGetRequest;
import com.example.monolithic.domain.member.dto.MemberPostRequest;
import com.example.monolithic.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

public interface MemberService {

    Member save(@NonNull final MemberPostRequest memberPostRequest);

    Member find(@NonNull final Long memberId);

    Page<Member> page(@NonNull final Pageable pageable, final MemberGetRequest memberGetRequest);
}
