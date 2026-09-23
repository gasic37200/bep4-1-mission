package com.back.global.global;

import com.back.global.eventPublisher.EventPublisher;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Handler;

// @Configuration은 스프링 빈으로 등록되어 스프링 컨테이너에서 관리되지만 싱글톤을 유지하는 어노테이션임
// 내부 @Bean 메서드 직접 호출하게 되면 new 연산자가 있을 때 @Configuration는 기존 객체를 반환해줌
@Configuration
public class GlobalConfig {
    @Getter
    // JPA Entity 같은 객체들은 스프링 빈이 아니므로 @Autowired나 생성자 주입(DI)을 받을 수 없음
    // 그래서 static을 사용하여 메모리에 등록함
    private static EventPublisher eventPublisher;

    // @Service로 등록되어있는 EventPublisher를 주입시켜 값을 받아옴
    @Autowired
    public static void setEventPublisher(EventPublisher eventPublisher) {
        GlobalConfig.eventPublisher = eventPublisher;
    }
}
