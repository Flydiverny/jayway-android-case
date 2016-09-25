package se.markusmaga.jayway.weather.details;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import se.markusmaga.jayway.weather.R;
import se.markusmaga.jayway.weather.mvp.BaseFragment;
import se.markusmaga.jayway.weather.network.models.City;
import se.markusmaga.jayway.weather.network.models.CityDayForecast;

/**
 * Created by Flydiverny on 25/09/16.
 */

public class DetailsFragment extends BaseFragment<DetailsContract.Presenter, DetailsContract.View> implements DetailsContract.View {

    private static final String CITY_ID = "city_id";

    private ProgressBar mLoading;
    private ForecastAdapter mAdapter;
    private TextView mCity, mRight;

    @Override
    protected void bindView(View view, Bundle savedInstanceState) {
        mLoading = (ProgressBar) view.findViewById(R.id.loading);
        mCity = (TextView) view.findViewById(R.id.city);
        mRight = (TextView) view.findViewById(R.id.right);

        RecyclerView forecasts = (RecyclerView) view.findViewById(R.id.forecasts);
        forecasts.setLayoutManager(new LinearLayoutManager(getActivity()));
        forecasts.setAdapter(mAdapter = new ForecastAdapter());
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
    public void showForecast(List<CityDayForecast> forecasts) {
        mAdapter.setItems(forecasts);
    }

    @Override
    public void showCityInfo(City city) {
        mCity.setText(city.getName());
        mRight.setText(city.getCountry());
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
