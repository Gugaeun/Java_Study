package spring;

import exception.DuplicateMemberDaoException;

import java.time.LocalDateTime;

public class MemberRegisterService {
    // MemberRegisterService클래스는 MemberDao클래스에 의존한다.
    // 의존은 변경에 의해 영향을 받는 관계
//    private MemberDao memberDao = new MemberDao(); // 의존 객체를 직접 생성
//    private MemberDao memberDao = new CachedMemberDao(); // 의존 객체를 직접 생성

    private MemberDao memberDao;

    // 의존하는 MemberDao객체를 직접 생성하는 것보다 의존 객체를 매개변수로 전달받는 방식 사용(DI추가)
    public MemberRegisterService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void register(RegisterRequest req) {
        // 회원 등록 전 체크 로직
        Member member = memberDao.selectByEmail(req.getEmail());
        if(member != null) {
            throw new DuplicateMemberDaoException("dup email " + req.getEmail());
        }

        // 회원 등록
        Member newMember = new Member(
                req.getEmail(), req.getPassword(), req.getName(),
                LocalDateTime.now());

        memberDao.insert(newMember);
    }
}
