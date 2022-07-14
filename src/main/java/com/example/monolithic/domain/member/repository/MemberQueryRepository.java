package com.example.monolithic.domain.member.repository;

import com.example.monolithic.domain.member.dto.MemberGetRequest;
import com.example.monolithic.domain.member.entity.Member;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import static com.example.monolithic.domain.member.entity.QMember.member;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberQueryRepository extends MemberQuerySupport {

	private final JPAQueryFactory jpaQueryFactory;

	public Page<Member> page(@NonNull final Pageable pageable, final MemberGetRequest memberGetRequest) {
		JPAQuery<Member> jpaQuery = jpaQueryFactory
				.select(member)
				.from()
				.where(where(memberGetRequest));
		return PageableExecutionUtils.getPage(jpaQuery.fetch(), pageable, jpaQuery::fetchCount);
	}
}
