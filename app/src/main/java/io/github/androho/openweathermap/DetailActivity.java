package io.github.androho.openweathermap;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;


import io.github.androho.openweathermap.adapter.RvAdapterForecast;
import io.github.androho.openweathermap.model.WeatherDay;
import io.github.androho.openweathermap.model.WeatherForecast;
import io.github.androho.openweathermap.services.WeatherAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends BaseActivity {
    public TextView cTemp, cPres;
    public ImageView imageView;
    public double lt, lg;
    public Call<WeatherForecast> callForecast;
    public String units = "metric", lang = "ru", currentCity;
    public String key = WeatherAPI.KEY;
    public WeatherForecast data;
    public WeatherDay weatherDay;
    public RecyclerView rvForecast;
    public RvAdapterForecast rvAdapterForecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        cTemp = (TextView) findViewById(R.id.currentTemp);
        cPres = (TextView) findViewById(R.id.currentPressure);
        imageView=(ImageView) findViewById(R.id.imageWeather);
        rvForecast=(RecyclerView)findViewById(R.id.rv_forecast);
        rvForecast.setLayoutManager(new LinearLayoutManager(this));
        getIntentResult();
        callForecast = api.getForecast(lt, lg, units, lang, key);
        getWeatherForecast();
        mTitle.setText(currentCity);
        cTemp.setText(weatherDay.getTempWithDegree()+ " C");
        cPres.setText("min: "+weatherDay.getTempMin()+"  max: "+weatherDay.getTempMax());
        Glide.with(this).load(weatherDay.getIconUrl()).into(imageView);

    }

    public void getWeatherForecast() {
        callForecast.enqueue(new Callback<WeatherForecast>() {
            @Override
            public void onResponse(Call<WeatherForecast> call, Response<WeatherForecast> response) {
                data = response.body();
                if (response.isSuccessful()) {
                    rvAdapterForecast = new RvAdapterForecast(this, data);
                    rvForecast.setAdapter(rvAdapterForecast);
                }
            }

            @Override
            public void onFailure(Call<WeatherForecast> call, Throwable t) {
            }
        });
    }

    public void getIntentResult() {
        Gson gson = new Gson();
        weatherDay = gson.fromJson(getIntent().getStringExtra("citiDayWeater"), WeatherDay.class);
        Intent it = getIntent();
        currentCity = it.getStringExtra("cityName");
        if (it != null) {
            Bundle params = it.getExtras();
            if (params != null) {
                lt = params.getDouble("cTemp");
                lg = params.getDouble("cPres");
            }
        }
    }
}

