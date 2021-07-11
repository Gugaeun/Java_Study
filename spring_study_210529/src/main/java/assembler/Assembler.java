package assembler;

import spring.*;

public class Assembler {
    private MemberDao memberDao;
    private MemberRegisterService regSvc;
    private ChangePasswordService pwdSvc;

    public Assembler() {
        memberDao = new MemberDao();
        regSvc = new MemberRegisterService(memberDao);
        pwdSvc = new ChangePasswordService();
        pwdSvc.setMemberDao(memberDao);

//        MemberDao memberDao1 = new MemberDao();
//        MemberDao memberDao1 = new CachedMemberDao();
//
//        MemberRegisterService regSvc1 = new MemberRegisterService(memberDao);
//        ChangePasswordService pwdSvc1 = new ChangePasswordService(memberDao);
    }

    public MemberDao getMemberDao() {
        return memberDao;
    }

    public MemberRegisterService getRegSvc() {
        return regSvc;
    }

    public ChangePasswordService getChangePasswordService() {
        return pwdSvc;
    }
}
