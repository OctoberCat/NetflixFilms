package com.netflixfilms.restClient;

import com.netflixfilms.model.Error;
import com.netflixfilms.model.Film;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by valentyn on 12.09.16.
 */
public class RESTClient {
    private static ApiRequests apiRequests;
    public static final String API_ENDPOINT = "http://netflixroulette.net/";
    private static OkHttpClient okHttpClient = new OkHttpClient();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = builder.client(okHttpClient).build();

    public static ApiRequests createRetrofitClient(Class<ApiRequests> requestsClass) {
        if (apiRequests == null) {

            return apiRequests = retrofit.create(requestsClass);
        } else {
            return apiRequests;
        }
    }

    public static Error parseError(retrofit2.Response<Film> response) {
        Converter<ResponseBody, Error> converter =
                retrofit.responseBodyConverter(Error.class, new Annotation[0]);

        Error error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new Error();
        }

        return error;
    }
}
