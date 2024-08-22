package org.eple0329.springbasic.controller;

import java.util.List;
import org.eple0329.springbasic.domain.Member;
import org.eple0329.springbasic.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

        private final MemberService memberService;

        // DI, 자동으로 MemberService를 넣어줌
        @Autowired
        public MemberController(MemberService memberService) {
                this.memberService = memberService;
        }

        @GetMapping(value = "/members/new")
        public String createForm() {
                return "members/createMemberForm";
        }

        @PostMapping(value = "/members/new")
        public String create(MemberForm form) {
                Member member = new Member();
                member.setName(form.getName());
                memberService.join(member);
                return "redirect:/";
        }

        @GetMapping(value = "/members")
        public String list(Model model) {
                List<Member> members = memberService.findMembers();
                model.addAttribute("members", members);
                return "members/memberList";
        }
}