package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import spring.dao.MemberDao;
import spring.helper.MemberPrinter;
import spring.model.entity.Member;

import java.util.Collection;

@Component("listPrinter")
public class MemberListPrinterService {
    private MemberDao memberDao;
    private MemberPrinter printer;

    @Autowired
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    @Autowired
    @Qualifier("printer")
//    public void setMemberPrinter(MemberSummaryPrinter printer) {
    public void setMemberPrinter(MemberPrinter printer) {
        this.printer = printer;
    }

    public MemberListPrinterService() { }

    public void printAll() {
        Collection<Member> members = memberDao.selectAll();
        members.forEach(m -> printer.print(m));
        System.out.println();
    }
}
