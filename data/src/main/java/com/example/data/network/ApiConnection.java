package com.example.data.network;

import android.provider.ContactsContract;
import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.Nullable;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ApiConnection implements Callable<String> {
    private static final String API_KEY = "c55b0432e85106d5b42b0fd6a15a07d4";

    private static final String API_KEY_TAG = "api_key";

    private static final String CONTENT_TYPE_LABEL = "Content-Type";

    private static final String CONTENT_TYPE_VALUE_JSON = "application/json; charset=utf-8";

    private static final String TAG = ApiConnection.class.getName();

    private String responseApi;

    private URL url;

    public ApiConnection(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    static ApiConnection createGet(String url) throws MalformedURLException {
        return new ApiConnection(url);
    }

    @Override
    public String call() throws Exception {
        return requestSyncCall();
    }

    /**
     * Do a request to an Api synchronously.
     * It should not be executed in the main thread of the application.
     */
    @Nullable
    String requestSyncCall() {
        connectToApi();
        return responseApi;
    }

    private void connectToApi() {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(String.valueOf(this.url)).newBuilder();
        urlBuilder.addQueryParameter(API_KEY_TAG, API_KEY);
        String urlBuilt = urlBuilder.build().toString();

        OkHttpClient okHttpClient = this.createClient();
        final Request request = new Request.Builder()
                .url(urlBuilt)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON)
                .get()
                .build();
        try {
            this.responseApi = okHttpClient.newCall(request).execute().body().string();
            Log.d(TAG, "API RESPONSE >> " + responseApi);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private OkHttpClient createClient() {
        final OkHttpClient okHttpClient = new OkHttpClient();
        return okHttpClient;
    }
}
