package se.markusmaga.jayway.weather.mvp;

/**
 * Created by Flydiverny on 24/09/16.
 */

public interface BaseContract {
    interface View {

    }

    interface Presenter<T extends View> {
        void attachView(T view);
        void detachView();
    }
}
