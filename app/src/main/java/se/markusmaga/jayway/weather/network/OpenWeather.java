package se.markusmaga.jayway.weather.network;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import se.markusmaga.jayway.weather.network.models.CityForecast;
import se.markusmaga.jayway.weather.network.models.SearchResponse;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by Flydiverny on 24/09/16.
 */
public interface OpenWeather {
    @GET("2.5/find?type=like")
    Call<SearchResponse> findCity(@Query("q") String search,
                                  @WeatherUnit @Query("units") String units,
                                  @Query("count") int count);

    @GET("2.5/forecast/daily")
    Call<CityForecast> searchById(@Query("id") int cityId,
                                  @WeatherUnit @Query("units") String units);

    @Retention(SOURCE)
    @StringDef({
            METRIC,
            IMPERIAL
    })
    @interface WeatherUnit {}
    String METRIC = "metric";
    String IMPERIAL = "imperial";
}
