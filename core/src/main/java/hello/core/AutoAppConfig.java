package hello.core;

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

}
