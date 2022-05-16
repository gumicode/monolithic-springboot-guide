package com.example.monolithic.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberPostRequest {

    private String username;
    private String password;
}
