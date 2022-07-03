package com.example.monolithic.domain.member.controller;

import com.example.monolithic.domain.member.dto.MemberPostRequest;
import com.example.monolithic.domain.member.entity.Member;
import com.example.monolithic.domain.member.property.MemberPath;
import com.example.monolithic.domain.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @MockBean
    MemberService memberService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("회원목록을 조회 합니다.")
    void getMembers() throws Exception {

        //given
        String username = "username1";
        Member returnMember = Member.builder()
                .id(1L)
                .username(username)
                .password("abcd1234!")
                .build();
        List<Member> members = List.of(returnMember);
        Page<Member> memberPage = PageableExecutionUtils.getPage(members, PageRequest.of(0, 10), () -> 10L);
        given(memberService.page(any(), any())).willReturn(memberPage);

        // when
        ResultActions resultActions = mockMvc.perform(get(MemberPath.MEMBERS)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());


        // then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("data.content[0].username").value(username));
    }

    @Test
    @DisplayName("회원을 생성 합니다. [조건] username 을 입력하지 않습니다.")
    void postMemberFailedUsernameBlank() throws Exception {

        // given
        MemberPostRequest memberPostRequest = MemberPostRequest.builder()
                .password("abcd1234!")
                .build();

        // when
        ResultActions resultActions = mockMvc.perform(post(MemberPath.MEMBERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberPostRequest)))
                .andDo(print());

        // then
        resultActions.andExpect(status().isBadRequest());
    }
}