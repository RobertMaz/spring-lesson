package ru.mtsbank.springboottest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mtsbank.springboottest.data.City;
import ru.mtsbank.springboottest.data.User;
import ru.mtsbank.springboottest.repository.UserRepository;
import ru.mtsbank.springboottest.svc.CityService;
import ru.mtsbank.springboottest.svc.UserService;

@SpringBootTest(classes = AppConfiguration.class)
class SpringBootTestApplicationTests {

  @Autowired
  private UserRepository userRepository;
  private UserService userService;
  @Autowired
  private CityService cityService;

  @BeforeEach
  public void init(){
    userService = new UserService();
    City city = new City();
    city.setName("SPB");
//    when(cityService.getCity("Moscow")).thenReturn(city);
  }


  @Test
  public void test(){
    userService.saveUser("Rob", "Moscow");
    for (User user : userRepository.getUsers()) {
      assertEquals(user.getCity().getName(), "Moscow");
    }
  }


}
