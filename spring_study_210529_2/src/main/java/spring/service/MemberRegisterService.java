package spring.service;

import config.ManualBean;
import exception.DuplicateMemberDaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spring.model.entity.Member;
import spring.dao.MemberDao;
import spring.model.dto.request.RegisterRequest;

import java.time.LocalDateTime;

//@ManualBean
@Service
public class MemberRegisterService {
    @Autowired
    private MemberDao memberDao;

    public MemberRegisterService() { }

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
