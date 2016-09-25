package se.markusmaga.jayway.weather.details;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import se.markusmaga.jayway.weather.R;
import se.markusmaga.jayway.weather.network.models.City;
import se.markusmaga.jayway.weather.network.models.CityDayForecast;
import se.markusmaga.jayway.weather.mvp.BaseFragment;

/**
 * Created by Flydiverny on 25/09/16.
 */

public class DetailsFragment extends BaseFragment<DetailsContract.Presenter, DetailsContract.View> implements DetailsContract.View {

    private static final String CITY_ID = "city_id";

    private ProgressBar mLoading;

    @Override
    protected void bindView(View view, Bundle savedInstanceState) {
        mLoading = (ProgressBar) view.findViewById(R.id.loading);
    }

    @Override
    protected void initPresenter() {
        mPresenter.init(getArguments().getInt(CITY_ID));
    }

    @Override
    protected DetailsContract.Presenter createPresenter() {
        return new DetailsPresenter();
    }

    @Override
    protected int layoutToInflate() {
        return R.layout.fragment_details;
    }

    public static DetailsFragment newInstance(int cityId) {
        Bundle args = new Bundle();
        args.putInt(CITY_ID, cityId);

        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void showForecast(List<CityDayForecast> forecast) {
        // TODO
    }

    @Override
    public void showCityInfo(City city) {
        // TODO
    }

    @Override
    public void showLoading() {
        mLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mLoading.setVisibility(View.GONE);
    }

    @Override
    public void showNetworkError() {
        Toast.makeText(getActivity(), R.string.network_error, Toast.LENGTH_SHORT).show();
    }
}
