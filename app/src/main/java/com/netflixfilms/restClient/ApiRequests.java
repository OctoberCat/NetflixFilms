package com.netflixfilms.restClient;

import com.netflixfilms.model.Film;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by valentyn on 12.09.16.
 */
public interface ApiRequests {
    @GET("api/api.php")
    Call<List<Film>> searchByDirector(@Query("director") String director);

    @GET("api/api.php")
    Call<Film> searchByTitle(@Query("title") String title);

}
