package se.markusmaga.jayway.weather.search

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.SearchView
import kotlinx.android.synthetic.main.fragment_search.*
import se.markusmaga.jayway.weather.R
import se.markusmaga.jayway.weather.activities.MainActivity
import se.markusmaga.jayway.weather.extensions.toast
import se.markusmaga.jayway.weather.models.SearchResult
import se.markusmaga.jayway.weather.mvp.BaseFragment

/**
 * Created by Flydiverny on 24/09/16.
 */

class SearchFragment : BaseFragment<SearchContract.Presenter, SearchContract.View>(), SearchContract.View {

    override fun navigateToDetails(id: Int) {
        if (activity is MainActivity) {
            (activity as MainActivity).navigateToDetails(id)
        }
    }

    private val _adapter: SearchAdapter = SearchAdapter {
        mPresenter.searchResultClicked(it)
    }

    override fun bindView(view: View, savedInstanceState: Bundle?) {
        search_field.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                mPresenter.search(query)
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                mPresenter.search(query)
                return true
            }
        })

        search_results.layoutManager = LinearLayoutManager(activity)
        search_results.adapter = _adapter
    }

    override fun createPresenter(): SearchContract.Presenter {
        return SearchPresenter()
    }

    override fun layoutToInflate(): Int {
        return R.layout.fragment_search
    }

    override fun searchFailedNetworkIssues() {
        toast(R.string.search_failed_network)
    }

    override fun searchFailedResponseIssues() {
        toast(R.string.search_failed_network)
    }

    override fun showSearchResults(list: List<SearchResult>) {
        _adapter.setSearchResults(list)
    }

    override fun showNoResults() {
        toast(R.string.search_no_results)
    }
}
