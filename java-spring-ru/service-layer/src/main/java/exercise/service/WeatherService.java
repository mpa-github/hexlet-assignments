package exercise.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import exercise.HttpClient;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import exercise.CityNotFoundException;
import exercise.repository.CityRepository;
import exercise.model.City;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class WeatherService {

    @Autowired
    CityRepository cityRepository;

    // Клиент
    HttpClient client;

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
    }

    // BEGIN
    public Map<String, Object> getCityWeather(String cityName) throws JsonProcessingException {
        String response = client.get(String.format("http://weather/api/v2/cities/%s", cityName));
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response, new TypeReference<Map<String, Object>>() { });
    }
    // END
}
