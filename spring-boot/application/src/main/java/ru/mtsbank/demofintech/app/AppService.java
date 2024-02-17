package ru.mtsbank.demofintech.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mtsbank.demofintech.Greeter;

import javax.annotation.PostConstruct;
import ru.mtsbank.demofintech.config.AppConfiguration;

@Service
public class AppService {

    @Autowired
    private AppConfiguration appConfiguration;
    @Autowired
    private Greeter greeter;


    @PostConstruct
    public void init() {
        greeter.morning();
    }

    public void print(){
        System.out.println("App name " + appConfiguration.getName());
        System.out.println("App value " + appConfiguration.getValue());
        System.out.println("App colour " + appConfiguration.getColour());
    }
}
