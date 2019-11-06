package io.github.androho.openweathermap.services;

import io.github.androho.openweathermap.model.WeatherDay;
import io.github.androho.openweathermap.model.WeatherForecast;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class WeatherAPI  {
    public static String KEY = "2082a5883774ad0329d10daa3ab451ba";
        public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
        private static Retrofit retrofit = null;

    public interface ApiInterface {
        @GET("weather")
        Call<WeatherDay> getToday(
                @Query("lat") Double lat,
                @Query("lon") Double lon,
                @Query("units") String units,
                @Query("lang")String lang,
                @Query("appid") String appid
        );

        @GET("forecast")
        Call<WeatherForecast> getForecast(
                @Query("lat") Double lat,
                @Query("lon") Double lon,
                @Query("units") String units,
                @Query("lang")String lang,
                @Query("appid") String appid
        );
    }


    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}