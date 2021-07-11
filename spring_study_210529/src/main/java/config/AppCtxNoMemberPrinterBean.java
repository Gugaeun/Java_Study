package config;

import org.springframework.context.annotation.Bean;
import spring.MemberDao;
import spring.MemberListPrinter;
import spring.MemberPrinter;

public class AppCtxNoMemberPrinterBean {
    private MemberPrinter printer = new MemberPrinter();	// 빈이 아님
    @Bean
    public MemberDao memberDao() {
        return new MemberDao();
    }

    @Bean
    public MemberListPrinter listPrinter() {
        return new MemberListPrinter(memberDao(), printer);
    }
}
