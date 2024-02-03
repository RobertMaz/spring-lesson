1.
```java
public class NotificationService {

   public void notify(String msg) {
      
   }
}

public class UserService {

   private NotificationService notificationService;

   public UserService(NotificationService notificationService) {
      this.notificationService = notificationService;
   }

   public void createUser(String... usernames) {
      for (String username : usernames) {
         String msg = "Hello " + username;
         notificationService.notify(msg);
      }
   }
}

class Application {

   public static void main(String[] args) {
      NotificationService notificationService = new NotificationService();
      UserService userService = new UserService(notificationService);
      userService.createUser("Robert");
   }
}
```

2.
```java
@Component
public class NotificationService {

  public void notify(String msg) {
    
  }
}
@Component
public class UserService {

  @Autowired
  private NotificationService notificationService;

  public void createUser(String... usernames) {
    for (String username : usernames) {
      String msg = "Hello " + username;
      notificationService.notify(msg);
    }
  }
}
@Configuration
@ComponentScan
public class Application {

  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
    UserService bean = context.getBean(UserService.class);
    bean.createUser("Robert");
  }
}
```
3. ApplicationContext(overview),ComponentScan, Component(overview), Configuration  
4. Autowired(Dependency Injection)(Constructor, Setter, Field)
   - demo: Constructor, Setter, Field
     - Bean
     ```java
     @Component
     public interface NotificationService {
       public void notify(String msg);
     }
     public class SmsNotificationService implements NotificationService {
     public void notify(){
     
     }
     }
     public class EmailNotificationService implements NotificationService{
     public void notify(){
     
     }
     }
     ```
     - List<Interface>
     - Map<String, Interface>
5. @Bean(scope=), (@Component, @Service, @Repository), @Controller, @Value, @Configuration, @Lazy
6. ApplicationContext(ClassPathXmlApplicationContext, AnnotationConfigApplicationContext, GenericGroovyApplicationContext)
7. ApplicationContext initialization
    - BeanDefinitionReader
    - BeanFactoryPostProcessor
    - FactoryBean<?> - создание кастомных FactoryBean(Фабрик бинов)
    - BeanFactory - Создание бинов
    - BeanPostProcessor(before, PostConstruct, after, PreDestroy)
    - 