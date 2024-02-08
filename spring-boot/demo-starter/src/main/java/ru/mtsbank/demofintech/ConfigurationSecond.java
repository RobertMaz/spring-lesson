package ru.mtsbank.demofintech;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationSecond {

    @Bean
    public Greeter greeter(GreetingConfig config){
        return new Greeter("Second", config);
    }
}
