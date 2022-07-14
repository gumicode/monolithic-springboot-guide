package com.example.monolithic.domain.member.repository;

import com.example.monolithic.domain.member.dto.request.MemberGetRequest;
import com.example.monolithic.domain.member.entity.Member;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPQLQuery;
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
public class MemberQueryRepository {
	private final JPAQueryFactory jpaQueryFactory;

	public Page<Member> page(@NonNull final Pageable pageable, final MemberGetRequest request) {

		JPQLQuery<Member> jpqlQuery = query(member, request)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize());
		return PageableExecutionUtils.getPage(jpqlQuery.fetch(), pageable, () -> query(member.count(), request).fetchFirst());
	}

	private <T> JPQLQuery<T> query(final Expression<T> select, final MemberGetRequest request) {
		return jpaQueryFactory
				.select(select)
				.from(member)
				.where(request.getWhere(member));
	}
}
