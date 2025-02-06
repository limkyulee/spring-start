package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemoryMemberRepository memberRepository;
    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
//        given
        Member member = new Member();
        member.setName("spring");
//        when
        Long saveId = memberService.join(member); // 회원가입 로직 실행
//        then
        Member findMember = memberService.findMember(saveId).get(); // 회원가입 실행한 맴버를 찾음
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName()); // 가입을 진행한 멤버와 동일한지 확인.
    }

    @Test
    public void 중복회원예외(){
//        Given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

//        When
        memberService.join(member1);
        // 해당 로직을 태울 떄, 오류가 발생해야함을 지정 (IllegalStateException > 이 에러가 터져야한다고 지정)
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

//        try{
//            memberService.join(member2);
//        }catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

//        Then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findMember() {
    }
}