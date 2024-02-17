package ru.mtsbank.springboottest.svc;

import org.springframework.stereotype.Service;
import ru.mtsbank.springboottest.data.City;

@Service
public class CityService {

  public City getCity(String city) {
    City createdCity = new City();
    createdCity.setName("Moscow");
    return createdCity;
  }
}
