package main;

import config.AppCtxAOP1;
import module.Calculator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAOPOrder {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx
                = new AnnotationConfigApplicationContext(AppCtxAOP1.class);

        Calculator calculator = ctx.getBean("calculator1", Calculator.class);
        calculator.factorial(7);
        calculator.factorial(7);
        calculator.factorial(5);
        calculator.factorial(5);

        ctx.close();
    }
}
