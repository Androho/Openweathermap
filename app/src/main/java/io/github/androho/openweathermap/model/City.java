package io.github.androho.openweathermap.model;

import java.util.ArrayList;
import java.util.List;

import io.github.androho.openweathermap.services.WeatherAPI;

public class City {
    public Double lat;
    public Double lng;
    public String lang;
    public String units;
    public String key;
    public String cityName;
    public City(Double lat, Double lng,String units,String lang,String key) {
        this.lat=lat;
        this.lng=lng;
        this.units=units;
        this.lang=lang;
        this.key=key;
    }
    private List<City> cityLists;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    private List<City> initializeDataCity() {
        cityLists = new ArrayList<>();
        cityLists.add(new City(50.45466, 30.5238, "metric", "ru", WeatherAPI.KEY));
        cityLists.add(new City(49.22, 28.409, "metric", "ru", WeatherAPI.KEY));
        cityLists.add(new City(48.023, 37.80224, "metric", "ru", WeatherAPI.KEY));
        cityLists.add(new City(44.95719, 34.11079, "metric", "ru", WeatherAPI.KEY));
        cityLists.add(new City(49.83826, 24.02324, "metric", "ru", WeatherAPI.KEY));
        return cityLists;
    }
}
