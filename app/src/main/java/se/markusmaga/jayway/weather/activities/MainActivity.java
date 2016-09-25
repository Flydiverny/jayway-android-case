package se.markusmaga.jayway.weather.activities;

import android.os.Bundle;

import se.markusmaga.jayway.weather.R;
import se.markusmaga.jayway.weather.details.DetailsFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void navigateToDetails(int cityId) {
        showFragment(DetailsFragment.newInstance(cityId));
    }
}
