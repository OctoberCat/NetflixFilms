package com.netflixfilms.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.netflixfilms.R;
import com.netflixfilms.eventBus.OttoBus;
import com.netflixfilms.eventBus.SearchResultEvent;
import com.netflixfilms.service.NetflixService;
import com.squareup.otto.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchFragment extends Fragment {


    public static final String ARG_SEARCH_TYPE = "search_type";
    private static final String TAG = SearchFragment.class.getSimpleName();
    @Bind(R.id.search_recycler)
    RecyclerView recyclerView;
    @Bind(R.id.search)
    EditText searchField;
    private boolean isTitleSearch;


    public SearchFragment() {
        // Required empty public constructor
    }

    @OnClick(R.id.search_btn)
    void searchMovie() {
        Log.i(TAG, "searchMovie: start");
        if (!TextUtils.isEmpty(searchField.getText().toString())) {
            String searchQuery = searchField.getText().toString().trim();
            if (isTitleSearch) {
                NetflixService.searchByTitle(getActivity(), searchQuery);
            } else {
                NetflixService.searchByDirector(getActivity(), searchQuery);
            }
        } else {
            Toast.makeText(getActivity(), "FUUUU", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        isTitleSearch = this.getArguments().getBoolean(ARG_SEARCH_TYPE);

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        OttoBus.getInstance().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        OttoBus.getInstance().unregister(this);
    }

    @Subscribe
    public void onResultReceieved(SearchResultEvent resultEvent) {
        Log.i(TAG, "onResultReceieved: " + resultEvent.getFilm());
        Log.i(TAG, "onResultReceieved: " + resultEvent.getFilmList());
    }
}
