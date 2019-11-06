package io.github.androho.openweathermap;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import io.github.androho.openweathermap.adapter.RvAdapterCity;
import io.github.androho.openweathermap.model.City;
import io.github.androho.openweathermap.services.WeatherAPI;

public class CityListActivity extends BaseActivity {
    public RecyclerView rvCity;
    public RvAdapterCity adapterCity;
    public List<City> cityLists;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_city);
        rvCity = (RecyclerView) findViewById(R.id.city_recycler_view);
        initializeDataCity();
        rvCity.setLayoutManager(new LinearLayoutManager(this));
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        initRecikler();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initRecikler();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void initRecikler() {
        adapterCity = new RvAdapterCity(this, cityLists);
        rvCity.setAdapter(adapterCity);
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
