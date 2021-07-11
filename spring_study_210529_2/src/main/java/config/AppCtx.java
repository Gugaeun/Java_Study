package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import spring.helper.MemberPrinter;
import spring.helper.MemberSummaryPrinter;

/*
@Component, @Service, @Repository 등의 애노테이션에 값을 주었는지에 따라 빈으로 등록할 때 사용할 이름이 지정된다.
@Component, @Service, @Repository 등의 애노테이션에 값을 주지 않으면, 클래스 이름의 첫 글자를 소문자로 바꾼 이름을 빈 이름으로 사용한다.
        ex) MemberDao => "memberDao", MemberRegisterService => "memberRegisterService"
@Component, @Service, @Repository 등의 애노테이션에 값을 주면 그 값을 빈 이름으로 사용한다.
        ex) MemberInfoPrinter => "infoPrinter", MemberListPrinter => "listPrinter"
 */
@Configuration
@ComponentScan(basePackages = {"spring.dao", "spring.model", "spring.service"})
public class AppCtx {

    @Bean
    @Qualifier("printer")
    public MemberPrinter memberPrinter1() {
        return new MemberPrinter();
    }
    @Bean
    @Qualifier("summaryPrinter")
    public MemberSummaryPrinter memberPrinter2() {
        return new MemberSummaryPrinter();
    }
}
