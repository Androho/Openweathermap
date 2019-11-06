package io.github.androho.openweathermap.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.github.androho.openweathermap.DetailActivity;
import io.github.androho.openweathermap.R;
import io.github.androho.openweathermap.holder.CityViewHolder;
import io.github.androho.openweathermap.model.City;
import io.github.androho.openweathermap.model.WeatherDay;
import io.github.androho.openweathermap.services.WeatherAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class RvAdapterCity extends RecyclerView.Adapter<CityViewHolder> {
    private Context context;
    private List<City> cities;
    public WeatherAPI.ApiInterface api;
    public View view;
    public City city;
    public WeatherDay[] dataSet;
    public String[] cityThis;
    public RvAdapterCity(Context context, List<City> cities){
        this.cities=cities;
        this.context=context;
        cityThis=new String[cities.size()];
        dataSet=new WeatherDay[cities.size()];
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item,parent,false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CityViewHolder holder, final int position) {
        city=cities.get(position);
        api = WeatherAPI.getClient().create(WeatherAPI.ApiInterface.class);
        Call<WeatherDay> callToday = api.getToday(city.lat, city.lng,city.units,city.lang,city.key );
        callToday.enqueue(new Callback<WeatherDay>() {
            @Override
            public void onResponse(Call<WeatherDay> call, Response<WeatherDay> response) {
                WeatherDay data = response.body();
                if (response.isSuccessful()) {
                    holder.tvCity.setText(data.getCity());
                    holder.tvTemp.setText(data.getTempWithDegree());
                    Glide.with(view).load(data.getIconUrl()).into(holder.ivIcon);
                    cityThis[position]=data.getCity();
                    dataSet[position]=data;
                }
            }
            @Override
            public void onFailure(Call<WeatherDay> call, Throwable t) {
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                getItemId(position);

                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                String citiDayWeater =gson.toJson(dataSet[position]);
                Bundle params=new Bundle();
                params.putDouble("cTemp",cities.get(position).lat);
                params.putDouble("cPres",cities.get(position).lng);
                intent.putExtras(params);
                intent.putExtra("cityName",cityThis[position]);
                intent.putExtra("citiDayWeater",citiDayWeater);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }
}
