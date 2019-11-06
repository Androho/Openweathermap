package io.github.androho.openweathermap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Calendar;

import io.github.androho.openweathermap.DetailActivity;
import io.github.androho.openweathermap.R;
import io.github.androho.openweathermap.holder.ForecastVievHolder;
import io.github.androho.openweathermap.model.WeatherForecast;
import retrofit2.Callback;

public class RvAdapterForecast extends RecyclerView.Adapter<ForecastVievHolder> {
    private WeatherForecast date;
    private Context context;
    Callback<WeatherForecast> callback;
    public View view;

    public RvAdapterForecast(Callback<WeatherForecast> callback, WeatherForecast data) {
        this.callback = callback;
        this.date = data;
        String est = "test";
        String two = "ggggg";
    }


    @NonNull
    @Override
    public ForecastVievHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_item, parent, false);
        return new ForecastVievHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ForecastVievHolder holder, final int position) {
        String dateTime = String.format("%d.%d.%d %d:%d",
                date.getItems().get(position).getDate().get(Calendar.DAY_OF_MONTH),
                date.getItems().get(position).getDate().get(Calendar.WEEK_OF_MONTH),
                date.getItems().get(position).getDate().get(Calendar.YEAR),
                date.getItems().get(position).getDate().get(Calendar.HOUR_OF_DAY),
                date.getItems().get(position).getDate().get(Calendar.MINUTE));
        holder.tvDate.setText(dateTime);
        Glide.with(view).load(date.getItems().get(position).getIconUrl()).into(holder.ivIcon);
        holder.tvTemp.setText(date.getItems().get(position).getTempMin() + "/" + date.getItems().get(position).getTempMax());
    }

    @Override
    public int getItemCount() {
        return date.getItems().size();
    }
}
