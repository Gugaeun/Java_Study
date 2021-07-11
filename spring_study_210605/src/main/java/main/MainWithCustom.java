package main;

import config.AppCtx;
import config.AppCtxWithCustom;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import spring.Client;
import spring.ClientWithCustom;

public class MainWithCustom {
    public static void main(String[] args) {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtxWithCustom.class);

        ClientWithCustom client2 = ctx.getBean(ClientWithCustom.class);
        client2.send();

        ctx.close();
    }
}
