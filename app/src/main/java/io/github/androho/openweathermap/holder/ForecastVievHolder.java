package io.github.androho.openweathermap.holder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.github.androho.openweathermap.R;
public class ForecastVievHolder extends RecyclerView.ViewHolder {
    public TextView tvDate, tvTemp;
    public ImageView ivIcon;
    public ForecastVievHolder(@NonNull View itemView) {
        super(itemView);
        tvDate =(TextView)itemView.findViewById(R.id.tv_forecast_date);
        tvTemp = (TextView)itemView.findViewById(R.id.tv_forecast_temp);
        ivIcon=(ImageView)itemView.findViewById(R.id.iv_forecast_weather);
    }
}
