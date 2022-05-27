package com.example.monolithic.domain.member.entity;

import com.example.monolithic.global.entity.BaseEntity;
import com.example.monolithic.global.entity.EntitySupport;
import com.example.monolithic.global.exception.DomainValidationException;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Slf4j
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member extends BaseEntity implements EntitySupport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Override
    public void validation() {
        if (username == null || username.isBlank()) {
            log.error("nickname : {}", username);
            throw new DomainValidationException();
        }

        if (password == null || password.isBlank()) {
            log.error("password : {}", password);
            throw new DomainValidationException();
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
