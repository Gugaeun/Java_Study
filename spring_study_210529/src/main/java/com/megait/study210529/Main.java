package com.megait.study210529;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppContext.class);

        Greeter g1 = ctx.getBean("greeter", Greeter.class);
        Greeter g2 = ctx.getBean("greeter", Greeter.class);
        System.out.println("(g1 == g2) = " + (g1 == g2));       // true

//        Greeter gg1 = new Greeter();
//        Greeter gg2 = new Greeter();
//        System.out.println("(gg1 == gg2) = " + (gg1 == gg2));   // false
        Greeter gg1 = Greeter.getInstance();
        Greeter gg2 = Greeter.getInstance();
        System.out.println("(gg1 == gg2) = " + (gg1 == gg2));   // true
    }
}
