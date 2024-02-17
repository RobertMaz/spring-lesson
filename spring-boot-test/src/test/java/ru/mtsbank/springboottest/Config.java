package ru.mtsbank.springboottest;

import jakarta.annotation.PostConstruct;
import java.util.List;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.mtsbank.springboottest.data.User;

@TestConfiguration
@Component
public class Config {

  @Bean
  public List<User> users() {
    List<User> users1 = List.of(new User());
    System.out.println("test123");
    return users1;
  }

}
