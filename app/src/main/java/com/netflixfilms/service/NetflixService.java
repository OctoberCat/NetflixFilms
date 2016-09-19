package com.netflixfilms.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.netflixfilms.eventBus.OttoBus;
import com.netflixfilms.eventBus.SearchResultEvent;
import com.netflixfilms.model.ApiError;
import com.netflixfilms.model.Film;
import com.netflixfilms.restClient.ApiRequests;
import com.netflixfilms.restClient.RESTClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Response;

public class NetflixService extends Service {
    private static final String EXTRA_DIRECTOR = "director";
    private static final String EXTRA_TITLE = "title";
    private static final String ACTION_TITLE = "search_title";
    private static final String ACTION_DIRECTOR = "search_director";
    private static final String TAG = NetflixService.class.getSimpleName();

    private ExecutorService executor;
    private String title;
    private String director;

    public NetflixService() {
    }

    public static void searchByTitle(Context context, String title) {
        Log.i(TAG, "searchByTitle: searching");
        Intent intent = new Intent(context, NetflixService.class);
        intent.setAction(ACTION_TITLE);
        intent.putExtra(EXTRA_TITLE, title);
        context.startService(intent);
    }

    public static void searchByDirector(Context context, String director) {
        Log.i(TAG, "searchByDirector: searching");
        Intent intent = new Intent(context, NetflixService.class);
        intent.setAction(ACTION_DIRECTOR);
        intent.putExtra(EXTRA_DIRECTOR, director);
        context.startService(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        executor = Executors.newCachedThreadPool();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();

        switch (action) {
            case ACTION_DIRECTOR:
                handleDirectorSearch(intent);
                break;
            case ACTION_TITLE:
                handleTitleSearch(intent);
                break;

        }

        return START_REDELIVER_INTENT;
    }

    private void handleTitleSearch(Intent intent) {
        title = intent.getStringExtra(EXTRA_TITLE);
        executor.submit(new Runnable() {
            @Override
            public void run() {

                ApiRequests apiRequest = RESTClient.createRetrofitClient(ApiRequests.class);
                Call<Film> call = apiRequest.searchByTitle(title);
                try {
                    Response<Film> response = call.execute();
                    if (response.isSuccessful()) {
                        Film film = response.body();
                        Log.i(TAG, "run: film: " + film.getSummary());
                        List<Film> filmList = new ArrayList<Film>();
                        filmList.add(film);
                        OttoBus.postOnMain(new SearchResultEvent(filmList));
                    } else {
                        ApiError apiError = RESTClient.parseError(response);
                        Log.i(TAG, "Error code: " + apiError.getErrorcode());
                    }
                } catch (IOException e) {
                    Log.i(TAG, " IOException during title search!");
                    e.printStackTrace();
                }

            }
        });
    }

    private void handleDirectorSearch(Intent intent) {
        Log.i(TAG, "handleDirectorSearch: start");
        director = intent.getStringExtra(EXTRA_DIRECTOR);
        executor.submit(new Runnable() {
            @Override
            public void run() {
                ApiRequests apiRequest = RESTClient.createRetrofitClient(ApiRequests.class);
                Call<List<Film>> call = apiRequest.searchByDirector(director);
                try {
                    Response<List<Film>> response = call.execute();
                    if (response.isSuccessful()) {
                        List<Film> filmList = response.body();
                        for (Film film : filmList) {
                            Log.i(TAG, "run: film " + film.getShowTitle());
                        }
                        OttoBus.postOnMain(new SearchResultEvent(filmList));
                    } else {
                        ApiError apiError = RESTClient.parseError(response);
                        Log.i(TAG, "Error code: " + apiError.getErrorcode());
                    }
                } catch (IOException e) {
                    Log.i(TAG, " IOException during director search!");
                }
            }
        });

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
