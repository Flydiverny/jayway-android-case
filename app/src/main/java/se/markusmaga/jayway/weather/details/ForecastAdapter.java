package se.markusmaga.jayway.weather.details;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import se.markusmaga.jayway.weather.R;
import se.markusmaga.jayway.weather.network.models.CityDayForecast;

/**
 * Created by Flydiverny on 25/09/16.s
 */
/* package */ class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {

    private List<CityDayForecast> mForecasts = new ArrayList<>();

    /* package */ void setItems(List<CityDayForecast> items) {
        if (items == null) {
            mForecasts = new ArrayList<>();
        } else {
            mForecasts = items;
        }

        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item_forecast, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CityDayForecast forecast = mForecasts.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE (dd/MM)", Locale.getDefault());
        String day = sdf.format(new Date(forecast.getDt()*1000L));

        holder.day.setText(day);
        holder.temperature.setText(String.format(Locale.getDefault(), "%.1f\u00B0%s", forecast.getTemp().getDay(), "C"));
    }

    @Override
    public int getItemCount() {
        return mForecasts.size();
    }

    /* package */ class ViewHolder extends RecyclerView.ViewHolder {
        TextView day;
        TextView temperature;
        ImageView image;

        ViewHolder(View itemView) {
            super(itemView);

            day = (TextView) itemView.findViewById(R.id.name);
            temperature = (TextView) itemView.findViewById(R.id.temperature);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
