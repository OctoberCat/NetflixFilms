package com.netflixfilms.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by valentyn on 15.09.16.
 */
public class FilmsRecyclerAdapter extends RecyclerView.Adapter<FilmsRecyclerAdapter.FilmViewHolder> {
    @Override
    public FilmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(FilmViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public FilmViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
