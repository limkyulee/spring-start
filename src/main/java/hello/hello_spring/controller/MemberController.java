package hello.hello_spring.controller;

import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// spting
@Controller
public class MemberController {

    private final MemberService memberService;

//    @Autowired > controller 와 service 를 연결 (의존관계 주입)
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
