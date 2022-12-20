package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// @Component 어노테이션이 붙은 것들을 모두 Bean으로 등록해줌, Spring Bean을 긁어오는 어노테이션
@ComponentScan(
        basePackages = "hello.core.member", // 탐색할 패키지의 시작위치를 지정함
        basePackageClasses = AutoAppConfig.class, // 해당 클래스의 위치부터 지정 (현재는 hello.core)
        // AppConfig에 있는 Bean은 수동으로 등록해야 하는데, @Configuration은 상위에 @Component가 붙어있으므로 예외처리 해준다.
        excludeFilters= @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)

public class AutoAppConfig {
    // Component를 통해 bean등록을 해주는 것과 수동으로 Bean 등록을 해주는 상황에서 Bean name을 동일하게 가져가면?
    // => Overriding bean definition for bean 'memoryMemberRepository' (수동 빈이 자동 빈 등록을 오버라이딩하여 우선권을 가져서 정상 실행)
    // => SpringBootApplication에서 실행하게 되면 수동 빈과 자동 빈 충돌 시 오류를 발생해준다.
    // => The bean 'memoryMemberRepository', defined in class path resource A bean with that name has already been defined in file
/*
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
*/
}
