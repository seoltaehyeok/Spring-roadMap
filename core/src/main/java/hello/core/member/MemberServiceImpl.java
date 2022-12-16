package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component // Bean 자동 등록
public class MemberServiceImpl implements MemberService{

    // 이제는 DIP를 지킬 수 있다 (추상화에만 의존)
    private final MemberRepository memberRepository;


    @Autowired // Component를 통해 Bean을 자동등록해도 의존관계를 주입할 수는 없기 때문에 @AutoWired로 의존관계 주입을 해주어야 한다.
    // 생성자를 통해 memberRepository에 어떤 구현체가 들어갈 지를 결정함
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findByMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}