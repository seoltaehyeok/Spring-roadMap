package hello.core;

        import hello.core.discount.DiscountPolicy;
        import hello.core.discount.RateDiscountPolicy;
        import hello.core.member.MemberRepository;
        import hello.core.member.MemberService;
        import hello.core.member.MemberServiceImpl;
        import hello.core.member.MemoryMemberRepository;
        import hello.core.order.OrderService;
        import hello.core.order.OrderServiceImpl;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;

// 애플리케이션 전체를 설정하고 구성함
// AppConfig.java와 appConfig.xml 두 개의 내용이 완전히 똑같음
@Configuration // 설정정보
public class AppConfig {
    // 각 메소드에 @Bean 등록을 하게 되면 Spring Container에 등록이 된다.
    @Bean
    // 역할을 다 파악할 수 있으며, private의 메서드를 호출하기 때문에 구현또한 AppConfig에서 파악할 수 있다.
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolish());
    }

    @Bean
    public DiscountPolicy discountPolish() {
//        return new FixDiscountPolish();
        return new RateDiscountPolicy();
    }
}