package se.markusmaga.jayway.weather.search

import se.markusmaga.jayway.weather.mvp.BaseContract
import se.markusmaga.jayway.weather.network.models.SearchResult

/**
 * Created by Flydiverny on 24/09/16.
 */
interface SearchContract {

    interface View : BaseContract.LceView {
        fun searchFailedResponseIssues()
        fun showSearchResults(list: List<SearchResult>)
        fun showNoResults()
        fun navigateToDetails(id: Int)
        fun showNoQuery()
    }

    interface Presenter : BaseContract.Presenter<View>  {
        fun autoSearch(query: String?)
        fun manualSearch(query: String?)
        fun searchResultClicked(it: SearchResult)
    }
}
