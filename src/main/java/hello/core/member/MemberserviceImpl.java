package hello.core.member;

public class MemberserviceImpl implements MemberService {

    private final MemberRepositoty memberRepositoty;

    public MemberserviceImpl(MemoryMemberRepository memberRepositoty) {
        this.memberRepositoty = memberRepositoty;
    }

    @Override
    public void join(Member member) {
        memberRepositoty.save(member);

    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepositoty.findById(memberId);
    }
}
