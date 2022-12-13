package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class RateDiscountPolishTest {
    RateDiscountPolish rateDiscountPolish = new RateDiscountPolish();

    @Test
    @DisplayName("VIP는 10%할인이 적용되어야 한다")
    void vip_o() {
        // given
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        // when
        int discount = rateDiscountPolish.discount(member, 10000);
        // then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니라면 할인적용되지 않아야 함")
    void vip_x() {
        // given
        Member member = new Member(2L, "memberBasic", Grade.BASIC);
        // when
        int discount = rateDiscountPolish.discount(member, 10000);
        // then
        assertThat(discount).isEqualTo(0);

    }

}