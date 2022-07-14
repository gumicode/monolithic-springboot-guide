package com.example.monolithic.global.dto.request;

import com.querydsl.core.types.dsl.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

public abstract class QueryDslExpression {

	public <T extends Number & Comparable<?>> BooleanExpression in(
			final NumberPath<T> path, final Collection<T> value) {
		return CollectionUtils.isEmpty(value) ? null : path.in(value);
	}

	public <T extends Number & Comparable<?>> BooleanExpression eq(
			final NumberPath<T> path, final T value) {
		return value == null ? null : path.eq(value);
	}

	public BooleanExpression eq(final StringPath path, final String value) {
		return StringUtils.hasText(value) ? path.eq(value) : null;
	}

	public BooleanExpression eq(final BooleanPath path, final Boolean value) {
		return value == null ? null : path.eq(value);
	}

	public <T extends Enum<T>> BooleanExpression eq(final EnumPath<T> path, final T value) {
		return value == null ? null : path.eq(value);
	}

	public <T extends Enum<T>> BooleanExpression in(
			final EnumPath<T> path, final List<T> value) {
		if (CollectionUtils.isEmpty(value)) {
			return null;
		}

		return path.in(value);
	}

	public BooleanExpression contains(final StringPath source, final String value) {
		return StringUtils.hasText(value) ? source.contains(value) : null;
	}

	public BooleanExpression compareNull(final StringPath path, final Boolean value) {
		if (value == null) {
			return null;
		}

		return value ? path.isNotNull() : path.isNull();
	}

	public BooleanExpression between(
			final DateTimePath<LocalDateTime> path, final LocalDate from, final LocalDate to) {
		if (from == null && to == null) {
			return null;
		}

		if (to == null) {
			return path.goe(from.atStartOfDay());
		}

		return from == null
				? path.loe(to.atTime(LocalTime.MAX))
				: path.between(from.atStartOfDay(), to.atTime(LocalTime.MAX));
	}
}
