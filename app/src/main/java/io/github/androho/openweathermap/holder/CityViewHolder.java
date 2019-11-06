package io.github.androho.openweathermap.holder;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.github.androho.openweathermap.R;

public class CityViewHolder extends RecyclerView.ViewHolder {
    public TextView tvCity, tvTemp;
    public ImageView ivIcon;
    public CityViewHolder(@NonNull View itemView) {
        super(itemView);
        tvCity =(TextView)itemView.findViewById(R.id.tvCity);
        tvTemp = (TextView)itemView.findViewById(R.id.tvTemp);
        ivIcon=(ImageView)itemView.findViewById(R.id.imageView);
    }
}
