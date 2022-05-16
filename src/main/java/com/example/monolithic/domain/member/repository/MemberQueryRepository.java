package com.example.monolithic.domain.member.repository;

import com.example.monolithic.domain.member.dto.MemberGetRequest;
import com.example.monolithic.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

public interface MemberQueryRepository {

    Page<Member> page(@NonNull final Pageable pageable, final MemberGetRequest memberGetRequest);
}
