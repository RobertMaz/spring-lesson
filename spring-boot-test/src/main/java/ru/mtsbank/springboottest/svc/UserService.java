package ru.mtsbank.springboottest.svc;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mtsbank.springboottest.data.User;
import ru.mtsbank.springboottest.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private CityService cityService;


  public User saveUser(String name, String city){
    User user = new User();
    user.setName(name);
    user.setCity(cityService.getCity(city));
    User saved = userRepository.save(user);
    return saved;
  }
}
