package se.markusmaga.jayway.weather.activities;

import android.os.Bundle;

import se.markusmaga.jayway.weather.R;
import se.markusmaga.jayway.weather.activities.base.BaseActivity;
import se.markusmaga.jayway.weather.details.DetailsFragment;
import se.markusmaga.jayway.weather.search.SearchFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            setDefaultFragment(SearchFragment.Factory.newInstance());
        }
    }

    public void navigateToDetails(int cityId) {
        showFragment(DetailsFragment.newInstance(cityId));
    }
}
