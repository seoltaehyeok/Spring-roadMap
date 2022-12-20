package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    public void AutowiredOption() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestBean.class); // Spring Bean으로 등록
    }

    // 임의의 테스트 클래스 생성
    static class TestBean {

        @Autowired(required = false)
        public void setNoBean1(Member noBean1) { // Member 객체는 Spring Bean에서 관리를 하지 않는다 => 아무거나 집어 넣어서 Bean 테스트
            System.out.println("noBean1 = " + noBean1); // Bean 객체가 없으면 메서드 자체가 호출 되지 않음
        }
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) { // noBean2 = null
            System.out.println("noBean2 = " + noBean2); // @Nullable로 인해 호출은 가능하오나 noBean2 값 자체는 null 값
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3) { // noBean3 = Optional.empty
            System.out.println("noBean3 = " + noBean3); // Bean으로 등록이 되어서 의존관계 주입은 o, noBean3은 Optional로 감싸주었기 때문에 empty값 반환
        }
    }
}
