package com.netflixfilms.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.activeandroid.query.Select;
import com.netflixfilms.R;
import com.netflixfilms.adapter.FilmsRecyclerAdapter;
import com.netflixfilms.eventBus.OttoBus;
import com.netflixfilms.model.Film;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedMoviesFragment extends Fragment {

    //  private OnFragmentInteractionListener mListener;
    @Bind(R.id.saved_movies_recycler)
    RecyclerView recyclerView;
    List<Film> savedFilms;

    public SavedMoviesFragment() {
        // Required empty public constructor
    }

    public List<Film> getAllFilms() {
        return new Select()
                .all()
                .from(Film.class)
                .execute();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_saved_movies, container, false);
        ButterKnife.bind(this, rootView);
        savedFilms = getAllFilms();
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), getResources().getConfiguration().orientation);
        recyclerView.setLayoutManager(layoutManager);
        FilmsRecyclerAdapter filmsRecyclerAdapter = new FilmsRecyclerAdapter(savedFilms);
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


}
