package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 클래스로 조회 시 자식이 둘 이상이면 중복 오류가 발생")
    public void findBeanByParentTypeDuplication() {
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                () -> applicationContext.getBean(DiscountPolicy.class));
    }
    @Test
    @DisplayName("부모 클래스로 조회 시 자식이 둘 이상이면 Bean 이름 지정")
    public void findBeanByParentTypeBeanName() {
        DiscountPolicy rateDiscountPolicy = applicationContext.getBean("rateDiscountPolish", DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    // 좋지 않은 방법
    @Test
    @DisplayName("특정 하위 타입(구체 타입)으로 조회")
    public void findBeanBySubType() {
        RateDiscountPolicy bean = applicationContext.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    public void findAllBeanByParentType() {
        Map<String, DiscountPolicy> beansOfType = applicationContext.getBeansOfType(DiscountPolicy.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }


    // Object 타입으로 조회하게 되면 하위 자손들 모두 조회하게 된다.
    // 따라서 Spring bean에 내가 등록하지 않고, Spring 내부적으로 등록되어 있던 Bean 또한 모두 출력된다.
    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object")
    public void findAllBeanByObjectType() {
        Map<String, Object> beansOfType = applicationContext.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
//        assertThat(beansOfType.size())
    }
    @Configuration
    public static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolish() {
            return new RateDiscountPolicy();
        }
        @Bean
        public DiscountPolicy fixDiscountPolish() {
            return new FixDiscountPolicy();
        }
    }

}
