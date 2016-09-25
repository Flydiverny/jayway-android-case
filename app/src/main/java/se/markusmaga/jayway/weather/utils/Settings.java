package se.markusmaga.jayway.weather.utils;

import android.content.Context;

import se.markusmaga.jayway.weather.network.OpenWeather;

/**
 * Created by Flydiverny on 25/09/16.
 */

public class Settings extends SharedPrefObject {
    private static final String WEATHER_UNIT = "weather_unit";

    public Settings(Context context) {
        super(context, "settings");
    }

    @OpenWeather.WeatherUnit
    public String getWeatherUnit() {
        //noinspection WrongConstant
        return mPreferences.getString(WEATHER_UNIT, OpenWeather.METRIC);
    }

    public void setWeatherUnit(@OpenWeather.WeatherUnit String weatherUnit) {
        edit(WEATHER_UNIT, weatherUnit);
    }
}
