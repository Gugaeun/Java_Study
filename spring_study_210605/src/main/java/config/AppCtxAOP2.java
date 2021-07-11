package config;

import aspect1.CacheAspect2;
import aspect1.ExeTimeAspect2;
import module.Calculator;
import module.CalculatorRecImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AppCtxAOP2 {
    @Bean
    public CacheAspect2 cacheAspect() {
        return new CacheAspect2();
    }
    @Bean
    public ExeTimeAspect2 exeTimeAspect() {
        return new ExeTimeAspect2();
    }
    @Bean
    public Calculator calculator() {
        return new CalculatorRecImpl();
    }
}
