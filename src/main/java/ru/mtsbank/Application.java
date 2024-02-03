package ru.mtsbank;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.mtsbank.app.UserService;

@ComponentScan("ru")
public class Application {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
    UserService bean = context.getBean(UserService.class);
    bean.createUser("Robert");
    System.out.println(bean);
    bean = context.getBean(UserService.class);
    bean.createUser("David");
    System.out.println(bean);
    context.close();
  }
}