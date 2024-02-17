package ru.mtsbank.demofintech.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;

@Service
public class SecondService {

    @Autowired
    private AppService appService;

    @PostConstruct
    public void init() throws NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        appService.print();
    }
}
