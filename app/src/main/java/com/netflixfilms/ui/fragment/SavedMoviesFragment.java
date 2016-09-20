package com.netflixfilms.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.activeandroid.query.Select;
import com.netflixfilms.R;
import com.netflixfilms.adapter.FilmsRecyclerAdapter;
import com.netflixfilms.model.Film;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedMoviesFragment extends Fragment {

    @Bind(R.id.saved_movies_recycler)
    RecyclerView recyclerView;

    private static final String RECYCLERVIEW_STATE = "recyclerview";

    private List<Film> savedFilms;
    private GridLayoutManager layoutManager;

    public SavedMoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_saved_movies, container, false);
        ButterKnife.bind(this, rootView);

        savedFilms = getAllFilms();

        layoutManager = new GridLayoutManager(getActivity(), getResources().getConfiguration().orientation);
        recyclerView.setLayoutManager(layoutManager);
        FilmsRecyclerAdapter filmsRecyclerAdapter = new FilmsRecyclerAdapter(savedFilms);
        recyclerView.setAdapter(filmsRecyclerAdapter);

        return rootView;
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

    public List<Film> getAllFilms() {
        return new Select()
                .all()
                .from(Film.class)
                .execute();
    }

}
