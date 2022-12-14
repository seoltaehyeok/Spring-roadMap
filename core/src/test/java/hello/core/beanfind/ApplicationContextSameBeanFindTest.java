package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolish;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    // NoUniqueBeanDefinitionException 에러
    // Spring Container에 등록된 두 개의 Bean(memberRepository1, memberRepository2)이 똑같은 MemoryMemberRepository()를 반환하기 때문
    @Test
    @DisplayName("타입으로 조회 시 같은 타입이 둘 이상 있으면 중복 오류가 발생한다.")
    public void findBeanTypeDuplicate() {
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                () -> applicationContext.getBean(MemberRepository.class));
    }

    @Configuration
    public static class SameBeanConfig {
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }

    @Test
    @DisplayName("타입으로 조회 시 같은 타입이 둘 이상 있으면 빈 이름을 지정하면 된다.")
    public void findBeanByname() {
//        MemberRepository memberRepository = applicationContext.getBean(MemberRepository.class); // 이 상태인 경우 에러
        MemberRepository memberRepository = applicationContext.getBean("memberRepository1", MemberRepository.class); // 따라서 Bean이름을 따로 지정해주면 됨
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    public void findAllBeanByType() {
        Map<String, MemberRepository> beansOfType = applicationContext.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }
}
