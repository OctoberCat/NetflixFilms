package com.netflixfilms.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.netflixfilms.model.Film;
import com.netflixfilms.ui.fragment.DetailsFragment;

import java.util.List;

/**
 * Created by valentyn on 18.09.16.
 */
public class FilmPagerAdapter extends FragmentStatePagerAdapter {
    List<Film> films;

    public FilmPagerAdapter(FragmentManager fm, List<Film> films) {
        super(fm);
        this.films = films;
    }

    @Override
    public Fragment getItem(int position) {
        return DetailsFragment.newInstance(films.get(position));
    }

    @Override
    public int getCount() {
        return films.size();
    }
}
