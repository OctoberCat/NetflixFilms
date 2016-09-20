package com.netflixfilms.eventBus;

import com.netflixfilms.model.ApiError;
import com.netflixfilms.model.Film;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by valentyn on 16.09.16.
 */
public class SearchResultEvent {
    ArrayList<Film> filmList;
    Film film;
    private ApiError error;

    public SearchResultEvent(List<Film> films) {
        this.filmList = (ArrayList<Film>) films;
    }

    public SearchResultEvent(ApiError error) {
        this.error = error;
    }

    public Film getFilm() {
        return film;
    }

    public ArrayList<Film> getFilmList() {
        return filmList;
    }

    public ApiError getError() {
        return error;
    }
}
