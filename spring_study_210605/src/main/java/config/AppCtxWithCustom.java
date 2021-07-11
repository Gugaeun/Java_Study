package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import spring.Client;
import spring.ClientWithCustom;

@Configuration
public class AppCtxWithCustom {
    @Bean
    @Scope("prototype")
    public Client client() {
        Client client = new Client();
        client.setHost("Client host");

        return client;
    }

    @Bean(initMethod = "startConnect", destroyMethod = "close")
    @Scope("singleton")
    public ClientWithCustom clientWithCustom() {
        ClientWithCustom client = new ClientWithCustom();
        client.setHost("ClientWithCustom host");

        return client;
    }
}
