package ru.mtsbank.app;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.mtsbank.entity.User;

//@Component
public class SmsNotificationServiceImpl implements NotificationService {

  @Override
  public void notify(User user) {
    
  }
}
