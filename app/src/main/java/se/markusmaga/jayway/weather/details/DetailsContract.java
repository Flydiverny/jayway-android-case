package se.markusmaga.jayway.weather.details;

import java.util.List;

import se.markusmaga.jayway.weather.network.models.City;
import se.markusmaga.jayway.weather.network.models.CityDayForecast;
import se.markusmaga.jayway.weather.mvp.BaseContract;

/**
 * Created by Flydiverny on 25/09/16.
 */
public interface DetailsContract {

    interface View extends BaseContract.LceView {

        void showForecast(List<CityDayForecast> forecast);

        void showCityInfo(City city);
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void init(int cityId);
    }
}
