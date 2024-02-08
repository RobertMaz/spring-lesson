package ru.mtsbank.app;

import org.springframework.stereotype.Component;
import ru.mtsbank.entity.User;

@Component
public interface NotificationService {


  void notify(User user);

}
