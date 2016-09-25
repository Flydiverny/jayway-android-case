package se.markusmaga.jayway.weather.search

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import kotlinx.android.synthetic.main.fragment_search.*
import se.markusmaga.jayway.weather.R
import se.markusmaga.jayway.weather.utils.Settings
import se.markusmaga.jayway.weather.activities.MainActivity
import se.markusmaga.jayway.weather.utils.kotlin.extensions.toast
import se.markusmaga.jayway.weather.mvp.BaseFragment
import se.markusmaga.jayway.weather.network.models.SearchResult

/**
 * Created by Flydiverny on 24/09/16.
 */

class SearchFragment : BaseFragment<SearchContract.Presenter, SearchContract.View>(), SearchContract.View {

    private val _adapter: SearchAdapter = SearchAdapter { mPresenter.searchResultClicked(it) }

    private var settings: Settings? = null

    override fun showLoading() {
        search_results.visibility = View.GONE
        loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        search_results.visibility = View.VISIBLE
        loading.visibility = View.GONE
    }

    override fun navigateToDetails(id: Int) {
        if (activity is MainActivity) {
            (activity as MainActivity).navigateToDetails(id)
        }
    }

    override fun bindView(view: View, savedInstanceState: Bundle?) {
        search_field.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                mPresenter.manualSearch(query)
                hideKeyboard()
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                mPresenter.autoSearch(query)
                return true
            }
        })

        search_results.layoutManager = LinearLayoutManager(activity)
        search_results.adapter = _adapter

        search_button.setOnClickListener {
            mPresenter.manualSearch(search_field.query.toString())
            hideKeyboard()
        }
    }

    private fun hideKeyboard() {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity.currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    override fun createPresenter(): SearchContract.Presenter {
        settings = Settings(activity)
        return SearchPresenter(settings!!)
    }

    override fun layoutToInflate(): Int {
        return R.layout.fragment_search
    }

    override fun showNetworkError() {
        toast(R.string.network_error)
    }

    override fun searchFailedResponseIssues() {
        toast(R.string.network_error)
    }

    override fun showSearchResults(list: List<SearchResult>) {
        _adapter.setSearchResults(list)
    }

    override fun showNoResults() {
        _adapter.clearSearchResults()
        toast(R.string.search_no_results)
    }

    override fun showNoQuery() {
        toast(R.string.no_query)
    }

    companion object Factory {
        @JvmStatic fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}