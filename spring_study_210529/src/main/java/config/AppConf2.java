package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.*;

@Configuration
public class AppConf2 {
    // @Autowired : 스프링의 자동 주입 기능을 위한 것이다.
    //  스프링 설정 클래스의 필드에 @Autowired 애노테이션을 붙이면
    //  해당 타입의 빈을 memberDao 필드에 할당한다.
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private MemberPrinter memberPrinter;

    @Bean
    public MemberRegisterService memberRegisterService() {
        // memberDao() 가 생성한 객체를 생성자를 통해 주입한다.
        return new MemberRegisterService(memberDao);
    }
    @Bean
    public ChangePasswordService changePasswordService() {
        // 의존 객체 주입
        ChangePasswordService passwordService = new ChangePasswordService();
        passwordService.setMemberDao(memberDao);
        return passwordService;
    }
    @Bean
    public MemberListPrinter listPrinter() {
        return new MemberListPrinter(memberDao, memberPrinter);
    }

    @Bean
    public MemberInfoPrinter infoPrinter() {
        MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
        // 의존 객체 주입
        infoPrinter.setMemberDao(memberDao);
        infoPrinter.setPrinter(memberPrinter);

        return infoPrinter;
    }

    @Bean
    public VersionPrinter versionPrinter() {
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(1);
        versionPrinter.setMiddleVersion(0);
        versionPrinter.setMinorVersion(0);

        return versionPrinter;
    }
}
