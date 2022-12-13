package hello.core;

import hello.core.discount.DiscountPolish;
import hello.core.discount.FixDiscountPolish;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// 애플리케이션 전체를 설정하고 구성함
public class AppConfig {
    // 역할을 다 파악할 수 있으며, private의 메서드를 호출하기 때문에 구현또한 AppConfig에서 파악할 수 있다.
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolish());
    }

    private DiscountPolish discountPolish() {
        return new FixDiscountPolish();
    }
}
