package ru.mtsbank.demofintech.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.mtsbank.demofintech.Greeter;

import javax.annotation.PostConstruct;

@Service
public class AppService {

    @Value("${application.name}")
    private String name;
    @Autowired
    private Greeter greeter;


    @PostConstruct
    public void init() {
        greeter.morning();
    }

    public String  getName(){
        System.out.println("Method invoked ");
        return name;
    }
}
