package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepositoty;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    //구현체
    private final MemberRepositoty memberRepositoty = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

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
