package hello.core.member;

public interface MemberRepositoty {

    // 맴버 저장
    void save(Member member);

    Member findById(Long memberId);
}
