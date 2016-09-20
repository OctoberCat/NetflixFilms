package com.netflixfilms.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.netflixfilms.R;
import com.netflixfilms.adapter.FilmsRecyclerAdapter;
import com.netflixfilms.eventBus.OttoBus;
import com.netflixfilms.eventBus.SearchResultEvent;
import com.netflixfilms.model.Film;
import com.netflixfilms.service.NetflixService;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchFragment extends Fragment {

    @Bind(R.id.search_recycler)
    RecyclerView recyclerView;
    @Bind(R.id.search)
    EditText searchField;
    @Bind(R.id.search_input)
    TextInputLayout inputLayout;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;
    @Bind(R.id.error_msg)
    TextView errorTextView;

    public static final String ARG_SEARCH_TYPE = "search_type";
    private static final String RECYCLERVIEW_STATE = "recyclerview";
    private static final String TAG = SearchFragment.class.getSimpleName();

    private List<Film> searchResult = new ArrayList<>();
    private boolean isTitleSearch;
    private FilmsRecyclerAdapter filmsRecyclerAdapter;
    private GridLayoutManager layoutManager;


    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        isTitleSearch = this.getArguments().getBoolean(ARG_SEARCH_TYPE);
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, rootView);

        if (isTitleSearch) {
            inputLayout.setHint(getString(R.string.search_title));
        } else {
            inputLayout.setHint(getString(R.string.search_director));
        }


        layoutManager = new GridLayoutManager(getActivity(), getResources().getConfiguration().orientation);
        recyclerView.setLayoutManager(layoutManager);
        filmsRecyclerAdapter = new FilmsRecyclerAdapter(searchResult);
        recyclerView.setAdapter(filmsRecyclerAdapter);
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(RECYCLERVIEW_STATE, layoutManager.onSaveInstanceState());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            layoutManager.onRestoreInstanceState(savedInstanceState.getParcelable(RECYCLERVIEW_STATE));
        }
    }

    @OnClick(R.id.search_btn)
    void searchMovie() {
        errorTextView.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(searchField.getText().toString())) {
            inputLayout.setErrorEnabled(false);
            String searchQuery = searchField.getText().toString().trim();
            progressBar.setVisibility(View.VISIBLE);
            if (isTitleSearch) {
                NetflixService.searchByTitle(getActivity(), searchQuery);
            } else {
                NetflixService.searchByDirector(getActivity(), searchQuery);
            }
        } else {
            inputLayout.setError(getString(R.string.error_empty));
        }
    }

    @Subscribe
    public void onResultReceieved(SearchResultEvent resultEvent) {
        progressBar.setVisibility(View.GONE);
        Log.i(TAG, "onResultReceieved: " + resultEvent.getFilmList());
        if (resultEvent.getError() == null) {
            filmsRecyclerAdapter.updateData(resultEvent.getFilmList());
        } else {
            errorTextView.setVisibility(View.VISIBLE);
            errorTextView.setText(resultEvent.getError().getMessage());

        }
    }


}
