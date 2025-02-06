package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 실제 비즈니스 로직을 작성.
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * @param member
     * @return
     */
    public Long join(Member member) {
//      같은 이름이 있는 중복 회원은 안됨.
//        Cmd + opt + v > 결과 자동 반환해줌
//        Optional > null을 직접 다루는 것을 피하고, 값이 있을 수도 없을 수도 있는 상황을 우아하게 처리하기 위함 래퍼 클래스.
//        NullPointerException 방지가능.
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다."); // Optional 이라서 가능.
//        });

        validateDupulicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDupulicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * 단일 회원 조회
     * @param id
     * @return Member
     */
    public Optional<Member> findMember(Long id){
        return memberRepository.findById(id);
    }
}
