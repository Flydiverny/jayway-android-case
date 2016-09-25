package se.markusmaga.jayway.weather.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Flydiverny on 25/09/16.
 */

public class DetailsFragment extends Fragment {

    private static final String CITY_ID = "city_id";

    public static DetailsFragment newInstance(int cityId) {

        Bundle args = new Bundle();
        args.putInt(CITY_ID, cityId);

        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);

        return fragment;
    }
}
