package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import spring.helper.MemberPrinter;
import spring.helper.MemberSummaryPrinter;

@Configuration
@ComponentScan(basePackages = {"spring.dao", "spring.model", "spring.service"}
//                , excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = ManualBean.class)}
            , excludeFilters = {
//                        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ChangePasswordService.class),
//                        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "spring\\..*"),
            }
)
public class AppCtxWithExclude {
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
