package se.markusmaga.jayway.weather.mvp;

/**
 * Created by Flydiverny on 24/09/16.
 */

public interface BaseContract {
    interface View {

    }

    interface LceView extends View {
        void showLoading();
        void hideLoading();
        void showNetworkError();
    }

    interface Presenter<T extends View> {
        void attachView(T view);
        void detachView();
    }
}
