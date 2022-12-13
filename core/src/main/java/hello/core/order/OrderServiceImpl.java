package hello.core.order;

import hello.core.discount.DiscountPolish;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{

//    아래의 코드는 구현체에 의존하고 있어, DIP를 만족하지 못하므로 생성자를 통해 추상화에만 의존하도록 해주어야 한다.
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolish discountPolish = new FixDiscountPolish();

    // 주문을 위해서는 member정보와 할인정책정보(discountPolish)가 필요
    private final MemberRepository memberRepository;
    private final DiscountPolish discountPolish;


    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolish discountPolish) {
        this.memberRepository = memberRepository;
        this.discountPolish = discountPolish;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolish.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
