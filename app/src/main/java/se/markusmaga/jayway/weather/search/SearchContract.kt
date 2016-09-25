package se.markusmaga.jayway.weather.search

import se.markusmaga.jayway.weather.models.SearchResult
import se.markusmaga.jayway.weather.mvp.BaseContract

/**
 * Created by Flydiverny on 24/09/16.
 */
interface SearchContract {

    interface View : BaseContract.View {
        fun searchFailedNetworkIssues()
        fun searchFailedResponseIssues()
        fun showSearchResults(list: List<SearchResult>)
        fun showNoResults()
        fun navigateToDetails(id: Int)
    }

    interface Presenter : BaseContract.Presenter<View>  {
        fun search(query: String?)
        fun searchResultClicked(it: SearchResult)
    }
}
