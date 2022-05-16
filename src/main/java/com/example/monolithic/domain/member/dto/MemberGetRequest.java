package com.example.monolithic.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberGetRequest {

    private Long id;
    private String username;
}
