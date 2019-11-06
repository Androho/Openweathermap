package io.github.androho.openweathermap;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.github.androho.openweathermap.services.WeatherAPI;

class BaseActivity extends AppCompatActivity {
    public WeatherAPI.ApiInterface api;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WeatherAPI.getClient().create(WeatherAPI.ApiInterface.class);

    }
}
