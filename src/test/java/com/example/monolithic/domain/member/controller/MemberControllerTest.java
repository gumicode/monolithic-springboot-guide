package com.example.monolithic.domain.member.controller;

import com.example.monolithic.domain.member.dto.MemberPostRequest;
import com.example.monolithic.domain.member.repository.MemberRepository;
import com.example.monolithic.domain.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Test
    public void thread() throws InterruptedException {

        memberRepository.deleteAll();

//        long start = System.currentTimeMillis();

        String password = "abcd1234!";
        for (int i = 0; i < 2000; i++) {

            MemberPostRequest request = new MemberPostRequest();
            request.setUsername("username" + i);
            request.setPassword(password);
//            memberService.registerMember(request);


            applicationEventPublisher.publishEvent(request);

        }

        Thread.sleep(20000);

//        long end = System.currentTimeMillis();
//
//        System.out.println(end - start + " 밀리초");
    }

}