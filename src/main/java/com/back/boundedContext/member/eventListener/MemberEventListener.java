package com.back.boundedContext.member.eventListener;

import com.back.boundedContext.member.entity.Member;
import com.back.boundedContext.member.service.MemberService;
import com.back.boundedContext.post.entity.Post;
import com.back.shared.post.event.PostCommentCreatedEvent;
import com.back.shared.post.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

@Component
@RequiredArgsConstructor
public class MemberEventListener {
    private final MemberService memberService;

    // Transactional(트랜잭션) + EventListener(이벤트 리스너)
    // 스프링 빈에 등록한 메서드 매개변수의 클래스 타입을 등록
    // publisher로 통해 매개변수 클래스 타입과 매핑하여 아래 메서드 실행
    // phase = AFTER_COMMIT 이벤트 발행한 곳의 트랜잭션이 성공한 후
    @TransactionalEventListener(phase = AFTER_COMMIT)
    // 새 트렌젝션을 열어서 이벤트를 실행
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(PostCreatedEvent event) {
        Member member = memberService.findById(event.getPost().getAuthorId()).get();

        member.increaseActivityScore(3);
    }

    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(PostCommentCreatedEvent event) {
        Member member = memberService.findById(event.getPostComment().getAuthorId()).get();

        member.increaseActivityScore(1);
    }
}
