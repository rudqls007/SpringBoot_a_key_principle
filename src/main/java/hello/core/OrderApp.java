package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberserviceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {

        AppConfig appconfig = new AppConfig();
        MemberService memberService = appconfig.memberservice();
        OrderService orderService = appconfig.orderService();
//        MemberService memberService = new MemberserviceImpl(null);
//        OrderService orderService = new OrderServiceImpl(null, null);


        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);


        System.out.println("order = " + order);
    }
}
