package hello.core.order;

import hello.core.discount.DiscountPolish;
import hello.core.discount.FixDiscountPolish;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{


    // 주문을 위해서는 member정보와 할인정책정보(discountPolish)가 필요
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolish discountPolish = new FixDiscountPolish();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolish.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);

    }
}
