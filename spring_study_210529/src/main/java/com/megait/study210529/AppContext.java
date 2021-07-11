package com.megait.study210529;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {
    @Bean
    public Greeter greeter() {
//        Greeter g = new Greeter();
        Greeter g = Greeter.getInstance();
        g.setFormat("%s, 안녕하세요~");

        return g;
    }
}
