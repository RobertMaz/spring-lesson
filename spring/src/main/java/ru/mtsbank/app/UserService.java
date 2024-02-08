package ru.mtsbank.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.mtsbank.entity.User;

@Service
@Scope(value = "singleton")
public class UserService {

  private NotificationService notificationService;
  private List<Object> list = new ArrayList<>();

  @PostConstruct
  public void init(){
    list.add(new Object());
  }

  @PreDestroy
  public void preDestroy(){
    
  }

  public UserService(NotificationService notificationService) {
    this.notificationService = notificationService;
    
  }

  public void createUser(String name) {
    User user = new User(name);
    notificationService.notify(user);
    System.out.println(notificationService.getClass());
  }

}
