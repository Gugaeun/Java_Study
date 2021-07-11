package main;

import config.AppCtx;
import config.AppCtxWithCustom;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import spring.Client;
import spring.ClientWithCustom;

public class Main2 {
    public static void main(String[] args) {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtxWithCustom.class);

        Client client1 = ctx.getBean(Client.class);
        Client client2 = ctx.getBean(Client.class);
        System.out.println("client1 == client2 : " + (client1 == client2));

        ClientWithCustom client3 = ctx.getBean(ClientWithCustom.class);
        ClientWithCustom client4 = ctx.getBean(ClientWithCustom.class);
        System.out.println("client3 == client4 : " + (client3 == client4));

        ctx.close();
    }
}
