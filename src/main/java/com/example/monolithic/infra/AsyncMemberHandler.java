package com.example.monolithic.infra;

import com.example.monolithic.domain.member.dto.MemberPostRequest;
import com.example.monolithic.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AsyncMemberHandler {

    private final MemberService memberService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Async
    @EventListener
    public void insertMemberAsync(MemberPostRequest memberPostRequest) {



        memberService.registerMember(memberPostRequest);

        SyncTemplate instance = SyncTemplate.getInstance();
        instance.print();

        System.out.println(memberPostRequest.getUsername());
    }

    @Async
    @EventListener
    public void insertMemberAsync2(Abcd abcd) {

        log.info("start!!");

        String password = "abcd1234!";
        for (int i = 0; i < 2000; i++) {

            MemberPostRequest request = new MemberPostRequest();
            request.setUsername("username" + i);
            request.setPassword(password);
            applicationEventPublisher.publishEvent(request);

        }

    }
}
