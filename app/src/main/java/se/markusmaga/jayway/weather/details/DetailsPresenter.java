package se.markusmaga.jayway.weather.details;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import se.markusmaga.jayway.weather.network.models.CityForecast;
import se.markusmaga.jayway.weather.network.OpenWeather;
import se.markusmaga.jayway.weather.network.RestMachine;

/**
 * Created by Flydiverny on 25/09/16.
 */
/* package */ class DetailsPresenter implements DetailsContract.Presenter {

    private DetailsContract.View mView;

    @Override
    public void attachView(DetailsContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void init(int cityId) {
        mView.showLoading();

        Call<CityForecast> call = RestMachine.getInstance().searchById(cityId, OpenWeather.METRIC);
        call.enqueue(new Callback<CityForecast>() {
            @Override
            public void onResponse(Call<CityForecast> call, Response<CityForecast> response) {
                if (mView != null) {
                    if (response.isSuccessful()) {
                        mView.showCityInfo(response.body().getCity());
                        mView.showForecast(response.body().getList());
                    } else {
                        mView.showNetworkError(); //TODO api error most likely and not network ~lazy
                    }

                    mView.hideLoading();
                }
            }

            @Override
            public void onFailure(Call<CityForecast> call, Throwable t) {
                if (mView != null) {
                    mView.hideLoading();
                    mView.showNetworkError();
                }
            }
        });
    }
}
