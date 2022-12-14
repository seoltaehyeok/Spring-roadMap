package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    public void findAllBean() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames(); // Bean으로 정의된 이름을 모두 등록
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = applicationContext.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + "object = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    public void findApplicationBean() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames(); // Bean으로 정의된 이름을 모두 등록
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = applicationContext.getBeanDefinition(beanDefinitionName);// Bean에 대한 metaData정보

            // Role ROLE_APPLICATION: 직접 스프링에 등록한 빈
            // Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
            // 현재 여기서는 appConfigObject, memberServiceObject, memberRepositoryObject, orderServiceObject, discountPolishoOject
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = applicationContext.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + "object = " + bean);
            }
        }
    }


}
