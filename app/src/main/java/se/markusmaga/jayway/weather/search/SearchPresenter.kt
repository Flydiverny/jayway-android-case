package se.markusmaga.jayway.weather.search

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import se.markusmaga.jayway.weather.models.SearchResponse
import se.markusmaga.jayway.weather.models.SearchResult
import se.markusmaga.jayway.weather.network.OpenWeather
import se.markusmaga.jayway.weather.network.RestMachine

/**
 * Created by Flydiverny on 24/09/16.
 */
class SearchPresenter : SearchContract.Presenter {

    private val TAG: String = "Search"

    override fun searchResultClicked(it: SearchResult) {
        _view?.navigateToDetails(it.id)
    }

    var _view: SearchContract.View? = null

    override fun attachView(view: SearchContract.View?) {
        _view = view
    }

    override fun detachView() {
        _view = null
    }

    override fun search(query: String?) {
        if (query is String && query.length > 3) {
            Log.v(TAG, "Search string: " + query)

            RestMachine.getInstance().findCity(query, OpenWeather.METRIC, 100)
                    .enqueue(object : Callback<SearchResponse> {
                                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                                    _view?.searchFailedNetworkIssues()
                                }

                                override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                                    if (response.isSuccessful) {
                                        if (response.body().list.size > 0) {
                                            _view?.showSearchResults(response.body().list)
                                        } else {
                                            _view?.showNoResults()
                                        }
                                    } else {
                                        _view?.searchFailedResponseIssues()
                                    }
                                }
                            })
        }
    }

}