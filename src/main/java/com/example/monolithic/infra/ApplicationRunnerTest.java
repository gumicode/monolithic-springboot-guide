package com.example.monolithic.infra;

import com.example.monolithic.domain.member.dto.MemberPostRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApplicationRunnerTest implements ApplicationRunner {

    private final ApplicationEventPublisher applicationEventPublisher;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        applicationEventPublisher.publishEvent(new Abcd());

    }
}
