package se.markusmaga.jayway.weather.search

import android.os.Handler
import android.text.TextUtils
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import se.markusmaga.jayway.weather.utils.Settings
import se.markusmaga.jayway.weather.network.RestMachine
import se.markusmaga.jayway.weather.network.models.SearchResponse
import se.markusmaga.jayway.weather.network.models.SearchResult

/**
 * Created by Flydiverny on 24/09/16.
 */
class SearchPresenter(private val settings: Settings) : SearchContract.Presenter {

    private val TAG: String = "Search"
    private var _view: SearchContract.View? = null
    private var _call: Call<SearchResponse>? = null

    private val handler: Handler = Handler()

    override fun attachView(view: SearchContract.View?) {
        _view = view
    }

    override fun detachView() {
        _view = null
    }

    override fun searchResultClicked(it: SearchResult) {
        _view?.navigateToDetails(it.id)
    }

    override fun manualSearch(query: String?) {
        if (TextUtils.isEmpty(query)) {
            _view?.showNoQuery()
        } else {
            search(query)
        }
    }

    override fun autoSearch(query: String?) {
        if (query is String && query.length >= 3) {
            search(query, false)
        } else {
            // Cancel any delayed search if user removed or cleared query
            cancelSearch()
            _view?.hideLoading()
        }
    }

    private fun cancelSearch() {
        _call?.cancel()
        handler.removeCallbacksAndMessages(null)
    }

    private fun search(query: String?, now: Boolean = true) {
        Log.v(TAG, "Search string: " + query)

        // If we have a call ongoing make sure to cancel it
        cancelSearch()

        val runnable = {
            _view?.showLoading()

            _call = RestMachine.getInstance().findCity(query, settings.weatherUnit, 100)
            _call!!.enqueue(object : Callback<SearchResponse> {
                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    if (!call.isCanceled) {
                        _view?.showNetworkError()
                        _view?.hideLoading()
                    } else {
                        Log.v(TAG, "Call was already canceled: " + query)
                    }
                }

                override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                    if (!call.isCanceled) {
                        if (response.isSuccessful) {
                            if (response.body().list.size > 0) {
                                _view?.showSearchResults(response.body().list)
                            } else {
                                _view?.showNoResults()
                            }
                        } else {
                            _view?.searchFailedResponseIssues()
                        }

                        _view?.hideLoading()
                    } else {
                        Log.v(TAG, "Call was already canceled: " + query)
                    }
                }
            })
        }

        if (now) {
            handler.post(runnable)
        } else {
            // delay 300 ms incase user is still typing
            handler.postDelayed(runnable, 300)
        }
    }
}