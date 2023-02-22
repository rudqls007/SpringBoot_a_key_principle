package hello.core;

import hello.member.Grade;
import hello.member.Member;
import hello.member.MemberService;
import hello.member.MemberserviceImpl;

public class MemberApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberserviceImpl();
        Member member =new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
