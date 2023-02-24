package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepositoty;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    //구현체
    private final MemberRepositoty memberRepositoty;
    // 인터페이스만 의존 ( DIP를 지킴 )
    private final DiscountPolicy discountPolicy;
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();


    public OrderServiceImpl(MemoryMemberRepository memberRepositoty, DiscountPolicy discountPolicy) {
        this.memberRepositoty = memberRepositoty;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 회원 조회
        Member member = memberRepositoty.findById(memberId);
        // 조회된 회원에 대한 할인
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 최종
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
