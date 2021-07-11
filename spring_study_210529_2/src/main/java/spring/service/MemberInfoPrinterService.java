package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.dao.MemberDao;
import spring.helper.MemberPrinter;
import spring.helper.MemberSummaryPrinter;
import spring.model.entity.Member;

@Component("infoPrinter")
public class MemberInfoPrinterService {
    private MemberDao memberDao;
    private MemberPrinter printer;

    @Autowired
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    @Autowired
    public void setPrinter(MemberSummaryPrinter printer) {
        this.printer = printer;
    }

    public void printMemberInfo(String email) {
        Member member = memberDao.selectByEmail(email);
        if(member == null) {
            System.out.println("데이터 없음\n");

            return;
        }
        printer.print(member);
        System.out.println();
    }
}
