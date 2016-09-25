package se.markusmaga.jayway.weather.mvp;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Flydiverny on 24/09/16.
 */

public abstract class BaseFragment<T extends BaseContract.Presenter<V>, V extends BaseContract.View> extends Fragment {

    protected T mPresenter;

    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(layoutToInflate(), container, false);
    }

    @CallSuper
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mPresenter = createPresenter();

        bindView(view, savedInstanceState);
        //noinspection unchecked
        mPresenter.attachView((V) this);
        initPresenter();
    }

    /**
     * Can be overridden to perform actions on presenter after attaching view.
     */
    protected void initPresenter() {
    }

    protected abstract void bindView(View view, Bundle savedInstanceState);

    protected abstract T createPresenter();

    @LayoutRes
    protected abstract int layoutToInflate();

    @CallSuper
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
    }
}
