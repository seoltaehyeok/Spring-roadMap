package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();
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
