package se.markusmaga.jayway.weather.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import se.markusmaga.jayway.weather.R;

/**
 * Created by Flydiverny on 25/09/16.
 */
public class BaseActivity extends AppCompatActivity {

    protected void setDefaultFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, fragment)
                .commit();
    }

    public void showFragment(Fragment frag) {
        showFragment(frag, frag.getClass().toString());
    }

    public void showFragment(Fragment frag, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, frag)
                .addToBackStack(tag)
                .commit();
    }
}
