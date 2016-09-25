package se.markusmaga.jayway.weather.search

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import se.markusmaga.jayway.weather.network.models.SearchResponse
import se.markusmaga.jayway.weather.network.models.SearchResult
import se.markusmaga.jayway.weather.network.OpenWeather
import se.markusmaga.jayway.weather.network.RestMachine

/**
 * Created by Flydiverny on 24/09/16.
 */
class SearchPresenter : SearchContract.Presenter {

    private val TAG: String = "Search"
    private var _view: SearchContract.View? = null

    override fun attachView(view: SearchContract.View?) {
        _view = view
    }

    override fun detachView() {
        _view = null
    }

    override fun searchResultClicked(it: SearchResult) {
        _view?.navigateToDetails(it.id)
    }

    private var _call: Call<SearchResponse>? = null

    override fun search(query: String?) {
        if (query is String && query.length > 3) {
            Log.v(TAG, "Search string: " + query)

            // If we have a call ongoing make sure to cancel it
            _call?.cancel()

            _view?.showLoading()

            _call = RestMachine.getInstance().findCity(query, OpenWeather.METRIC, 100)
            _call?.enqueue(object : Callback<SearchResponse> {
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
    }
}