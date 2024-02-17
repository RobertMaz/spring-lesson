package ru.mtsbank.springboottest.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Repository;
import ru.mtsbank.springboottest.data.User;

@Repository
@ConditionalOnClass()
public class UserRepository {

  private List<User> users = new ArrayList<>();

  public User save(User user) {
    users.add(user);
    user.setId(UUID.randomUUID());
    return user;
  }

  public List<User> getUsers(){
    return new ArrayList<>(users);
  }
}
