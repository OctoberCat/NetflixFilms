package com.netflixfilms.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.netflixfilms.R;
import com.netflixfilms.model.Film;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {
    @Bind(R.id.director_details)
    TextView director;
    @Bind(R.id.rating_details)
    TextView rating;
    @Bind(R.id.summary)
    TextView summary;
    @Bind(R.id.release_year_details)
    TextView year;
    @Bind(R.id.poster_details)
    ImageView poster;

    private static final String ARG_FILM = "argFilm";

    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance(Film film) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_FILM, film);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, rootView);

        Film film = getArguments().getParcelable(ARG_FILM);

        rating.setText(film.getRating());
        director.setText(film.getDirector());
        year.setText(film.getReleaseYear());
        summary.setText(film.getSummary());
        Picasso.with(getActivity()).load(film.getPoster()).into(poster);
        return rootView;

    }

}
