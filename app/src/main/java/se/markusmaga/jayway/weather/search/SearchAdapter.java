package se.markusmaga.jayway.weather.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import se.markusmaga.jayway.weather.R;
import se.markusmaga.jayway.weather.network.models.SearchResult;

/**
 * Created by Flydiverny on 25/09/16.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private final OnSearchResultClickedListener mListener;
    private List<SearchResult> mSearchResults = new ArrayList<>();

    public SearchAdapter(OnSearchResultClickedListener listener) {
        mListener = listener;
    }

    public void setSearchResults(List<SearchResult> searchResults) {
        if (searchResults == null) {
            mSearchResults = new ArrayList<>();
        } else {
            mSearchResults = searchResults;
        }

        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item_city, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SearchResult city = mSearchResults.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onSearchResultClicked(city);
            }
        });

        holder.name.setText(String.format("%s (%s)", city.getName(), city.getSys().getCountry()));
        holder.temperature.setText(String.format(Locale.getDefault(), "%.1f\u00B0%s", city.getMain().getTemp(), "C"));
    }

    @Override
    public int getItemCount() {
        return mSearchResults.size();
    }

    public void clearSearchResults() {
        mSearchResults.clear();
        notifyItemRangeRemoved(0, mSearchResults.size());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView temperature;
        ImageView image;

        ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            temperature = (TextView) itemView.findViewById(R.id.temperature);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }

    public interface OnSearchResultClickedListener {
        void onSearchResultClicked(SearchResult searchResult);
    }
}
