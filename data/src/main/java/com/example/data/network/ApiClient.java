package com.example.data.network;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String API_KEY = "c55b0432e85106d5b42b0fd6a15a07d4";

    public static final String BACKDROP_BASE_URL = "https://image.tmdb.org/t/p/w780/";

    public static final String BASE_URL = "http://api.themoviedb.org/3/";

    public static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w200/";

    private static final String API_KEY_TAG = "api_key";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .addInterceptor(chain -> {
                        Request original = chain.request();
                        HttpUrl httpUrl = original.url();

                        HttpUrl newHttpUrl = httpUrl.newBuilder()
                                .addQueryParameter(API_KEY_TAG, API_KEY).build();

                        Request.Builder requestBuilder = original.newBuilder().url(newHttpUrl);

                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    });

            OkHttpClient okHttpClient = builder.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
