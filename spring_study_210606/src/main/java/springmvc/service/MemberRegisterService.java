package springmvc.service;

import java.time.LocalDateTime;

import exceptions.DuplicateMemberDaoException;
import springmvc.model.Member;
import springmvc.dao.MemberDao;
import springmvc.model.RegisterRequest;

public class MemberRegisterService {
	private MemberDao memberDao;
	
	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public Long regist(RegisterRequest req) {
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
        
        return newMember.getId();
	}
}
