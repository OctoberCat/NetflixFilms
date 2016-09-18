package com.netflixfilms.restClient;


import com.netflixfilms.model.ApiError;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by valentyn on 12.09.16.
 */
public class RESTClient {
    public static final String API_ENDPOINT = "http://netflixroulette.net/";
    private static ApiRequests apiRequests;
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

    public static ApiError parseError(Response<?> response) {
        Converter<ResponseBody, ApiError> converter =
                retrofit.responseBodyConverter(ApiError.class, new Annotation[0]);

        ApiError apiError;

        try {
            apiError = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ApiError();
        }

        return apiError;
    }
}
