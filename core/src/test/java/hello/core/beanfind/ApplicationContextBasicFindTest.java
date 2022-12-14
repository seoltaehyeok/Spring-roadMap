package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    public void findByName() {
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름없이 타입으로만 조회")
    public void findByType() {
        MemberService memberService = applicationContext.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }


    // 역할에 의존o 구현에 의존x => 좋은 코드는 아님
    @Test
    @DisplayName("구체타입으로 조회")
    public void findByImplType() {
        MemberService memberService = applicationContext.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }


    // 실패 테스트 : NoSuchBeanDefinitionException: No bean named 'xxxx' available
    @Test
    @DisplayName("빈 이름으로 조회X")
    public void findByBeanFail() {
//        applicationContext.getBean("xxxx", MemberService.class); 존재하지 않는 Bean name
//        MemberService xxxx = applicationContext.getBean("xxxx", MemberService.class);
        // 해당 예외가 터지면 Test 성공 그렇지 않으면 실패
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                () -> applicationContext.getBean("xxxx", MemberService.class));
    }

}
