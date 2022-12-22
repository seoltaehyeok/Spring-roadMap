package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class PrototypeTest {

    @Test
    public void prototypeBeanFind() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(prototypeBean.class);
        System.out.println("find prototypeBean1");
        prototypeBean prototypeBean1 = applicationContext.getBean(prototypeBean.class); // init() 호출됨
        System.out.println("find prototypeBean2");
        prototypeBean prototypeBean2 = applicationContext.getBean(prototypeBean.class); // init() 호출됨 => SpringContainer에 요청할 때마다 빈이 새로 생성된다.
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2); // 서로 다른 빈 값을 반환하기 때문에 isNotSame 테스트를 해야 함

        applicationContext.close(); // destroy() 호출되지 않음 - @Scope("prototype")의 특징
    }

    @Scope("prototype")
    static class prototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("prototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("prototypeBean.destroy");
        }
    }
}
