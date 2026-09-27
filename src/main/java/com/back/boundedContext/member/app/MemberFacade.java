package com.back.boundedContext.member.app;

import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.member.domain.MemberPolicy;
import com.back.global.exception.DomainException;
import com.back.boundedContext.member.out.MemberRepository;
import com.back.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberFacade {
    private final MemberJoinUseCase memberJoinUseCase;
    private final MemberPolicy memberPolicy;
    private final MemberRepository memberRepository;

    public RsData<Member> join(String username, String password, String nickname) {
        return memberJoinUseCase.join(username, password, nickname);
    }

    public String getRandomSecureTip() {
        return "비밀번호의 유효기간은 %d일 입니다."
                .formatted(memberPolicy.getNeedToChangePasswordDays());
    }

    public long count() {
        return memberRepository.count();
    }

    public Optional<Member> findById(int id) {
        return memberRepository.findById(id);
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }
}