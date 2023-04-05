package exercise.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.CityNotFoundException;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherService weatherService;

    // BEGIN
    @GetMapping(path = "/cities/{id}")
    public Map<String, Object> getWeatherByCity(@PathVariable(name = "id") long id) throws JsonProcessingException {
        City city = cityRepository.findById(id)
            .orElseThrow(() -> new CityNotFoundException("City not found!"));
        return weatherService.getCityWeather(city.getName());
    }

    @GetMapping(path = "/search")
    public List<Map<String, Object>> searchCityByName(@RequestParam(name = "name", required = false) String cityName) {
        List<Map<String, Object>> citiesDto = new ArrayList<>();
        List<City> cities;
        List<Map<String, Object>> cityWeatherList = new ArrayList<>();
        if (cityName == null) {
            cities = cityRepository.findAllByOrderByNameAsc();
        } else {
            cities = cityRepository.findByNameStartingWithIgnoreCase(cityName);
        }
        cities.forEach(city -> {
            try {
                cityWeatherList.add(weatherService.getCityWeather(city.getName()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        cityWeatherList.forEach(weather -> citiesDto.add(new LinkedHashMap<>(){{
            put("temperature", weather.get("temperature"));
            put("name", weather.get("name"));
        }}));
        return citiesDto;
    }
    // END
}

