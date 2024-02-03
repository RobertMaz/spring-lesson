package ru.mtsbank.app;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.mtsbank.entity.User;

@Component
//@DependsOn(value = "userService")
public class EmailNotificationServiceImpl implements NotificationService {

  public EmailNotificationServiceImpl() {
    
  }

  @Override
  public void notify(User user) {
    
  }
}
