package com.netflixfilms.adapter;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.netflixfilms.R;
import com.netflixfilms.model.Film;
import com.netflixfilms.ui.activity.DetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by valentyn on 15.09.16.
 */
public class FilmsRecyclerAdapter extends RecyclerView.Adapter<FilmsRecyclerAdapter.FilmViewHolder> {
    List<Film> films;

    public FilmsRecyclerAdapter(List<Film> films) {
        this.films = films;
    }

    @Override
    public FilmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_item, parent, false);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FilmViewHolder holder, int position) {
        Film film = films.get(position);
        holder.title.setText(film.getShowTitle());
        holder.category.setText(film.getCategory());
        holder.rating.setText(film.getRating());
        holder.director.setText(film.getDirector());
        holder.releaseYear.setText(film.getReleaseYear());
        holder.film = film;
        Picasso.with(holder.poster.getContext()).load(Uri.parse(film.getPoster())).into(holder.poster);

    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public void updateData(ArrayList<Film> filmList) {
        films.clear();
        films.addAll(filmList);
        notifyDataSetChanged();
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final String TAG = FilmViewHolder.class.getSimpleName();

        @Bind(R.id.poster)
        ImageView poster;
        @Bind(R.id.show_title)
        TextView title;
        @Bind(R.id.director)
        TextView director;
        @Bind(R.id.rating)
        TextView rating;
        @Bind(R.id.production_year)
        TextView releaseYear;
        @Bind(R.id.category)
        TextView category;
        Film film;

        public FilmViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.i(TAG, "onClick: film title: " + film.getShowTitle());
            Intent intent = new Intent(view.getContext(), DetailsActivity.class);
            intent.putParcelableArrayListExtra(DetailsActivity.DETAILS_KEY, (ArrayList<? extends Parcelable>) films);
            view.getContext().startActivity(intent);
        }
    }
}
