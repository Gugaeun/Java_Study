package spring.service;

import config.ManualBean;
import exception.MemberNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spring.model.entity.Member;
import spring.dao.MemberDao;

//@ManualBean
@Service
public class ChangePasswordService {
    @Autowired
    private MemberDao memberDao;

    public ChangePasswordService() { }

    public void changePassword(String email, String oldPwd, String newPwd) {
        Member member = memberDao.selectByEmail(email);
        if (member == null)
            throw new MemberNotFoundException();

        member.changePassword(oldPwd, newPwd);
        memberDao.update(member);
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
}
