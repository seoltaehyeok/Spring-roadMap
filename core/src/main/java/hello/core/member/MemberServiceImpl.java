package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // 이제는 DIP를 지킬 수 있다 (추상화에만 의존)
    private final MemberRepository memberRepository;


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
}