package ru.mtsbank.springboottest;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.mtsbank.springboottest.svc.CityService;

@TestConfiguration
public class AppConfiguration {

  @Bean
  public CityService cityService() {
    return new CityService();
  }
}