package main;

import config.AppCtxAOP2;
import module.Calculator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAOP2 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppCtxAOP2.class);

        Calculator cal = ctx.getBean("calculator", Calculator.class);
        cal.factorial(7);
        cal.factorial(7);
        cal.factorial(5);
        cal.factorial(5);

        ctx.close();
    }
}
