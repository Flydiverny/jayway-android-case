package se.markusmaga.jayway.weather.network;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import se.markusmaga.jayway.weather.Constants;

/**
 * Created by Flydiverny on 24/09/16.
 */

public final class RestMachine {

    private static OpenWeather INSTANCE;

    private RestMachine() {
        // hide constructor plz
    }

    public static OpenWeather getInstance() {
        if (INSTANCE == null) {
            // Not threadsafe etc, yes yes I know. enum method maybe? =D
            INSTANCE = new RestMachine().getRetrofit().create(OpenWeather.class);
        }

        return INSTANCE;
    }

    private Retrofit getRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient());

        return builder.build();
    }

    public OkHttpClient getClient() {
        return new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder();
                HttpUrl.Builder httpUrlBuilder = original.url().newBuilder();

                httpUrlBuilder
                        .addQueryParameter("APPID", Constants.API_KEY);

                requestBuilder.url(httpUrlBuilder.build());

                return chain.proceed(requestBuilder.build());
            }
        }).build();
    }
}
