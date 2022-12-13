package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach // 테스트가 실행 전 무조건 미리 실행 (테스트의 개수마다)
    public void beforeEach() {
        AppConfig appconfig = new AppConfig();
        memberService = appconfig.memberService();
    }

    @Test
    void join() {
        // given : memeber가 주어질 때
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when : 회원가입을 했을 때
        memberService.join(member);
        Member findMember = memberService.findByMember(1L);
        // then : 결과는 ?
        Assertions.assertThat(member).isEqualTo(findMember);

    }
}
