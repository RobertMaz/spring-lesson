package ru.mtsbank.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceBeanPostProcessor implements BeanFactoryPostProcessor {

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
      throws BeansException {
    
    BeanDefinition beanDefinition = beanFactory.getBeanDefinition("emailNotificationServiceImpl");
    beanDefinition.setBeanClassName("ru.mtsbank.app.SmsNotificationServiceImpl");

  }
}
