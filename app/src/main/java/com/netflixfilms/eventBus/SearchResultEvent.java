package com.netflixfilms.eventBus;

import com.netflixfilms.model.Film;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by valentyn on 16.09.16.
 */
public class SearchResultEvent {
    ArrayList<Film> filmList;
    Film film;

    public SearchResultEvent(List<Film> films) {
        this.filmList = (ArrayList<Film>) films;
    }

    public SearchResultEvent(Film film) {
        this.film = film;
    }

    public Film getFilm() {
        return film;
    }

    public ArrayList<Film> getFilmList() {
        return filmList;
    }
}
