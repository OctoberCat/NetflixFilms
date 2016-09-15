package com.netflixfilms.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.netflixfilms.model.Film;
import com.netflixfilms.restClient.ApiRequests;
import com.netflixfilms.restClient.RESTClient;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;

public class NetflixService extends Service {
    private static final String EXTRA_DIRECTOR = "director";
    private static final String EXTRA_TITLE = "title";
    private static final String ACTION_TITLE = "search_title";
    private static final String ACTION_DIRECTOR = "search_director";
    private static final String TAG = NetflixService.class.getSimpleName();

    private ExecutorService executor;
    private String title;
    private Film film;

    public NetflixService() {
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
                    retrofit2.Response<Film> response = call.execute();
                    if (response.isSuccessful()) {
                        film = response.body();
                    } else {
                        RESTClient.parseError(response);
                        //For getting error message
                        Log.d("Error message", response.message());
                        //For getting error code. Code is integer value like 200,404 etc
                        Log.d("Error code", String.valueOf(response.code()));
                    }
                } catch (IOException e) {
                    Log.i(TAG, " IOException during title search!");
                    e.printStackTrace();
                }

            }
        });
    }

    private void handleDirectorSearch(Intent intent) {
        String director = intent.getStringExtra(EXTRA_DIRECTOR);

    }

    public static void searchByTitle(Context context, String title) {
        Intent intent = new Intent(context, NetflixService.class);
        intent.setAction(ACTION_TITLE);
        intent.putExtra(EXTRA_TITLE, title);
        context.startService(intent);
    }

    public static void searchByDirector(Context context, String director) {
        Intent intent = new Intent(context, NetflixService.class);
        intent.setAction(ACTION_DIRECTOR);
        intent.putExtra(EXTRA_DIRECTOR, director);
        context.startService(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
