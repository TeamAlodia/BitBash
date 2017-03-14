package com.alodia.bitbash.services;

import android.util.Log;

import com.alodia.bitbash.Constants;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Alaina Traxler on 3/14/2017.
 */

public class GamesDbService {
    public void findAllPlatforms(final Callback callback) {
        final OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.URL_GETPLATFORMS).newBuilder();
        String url = urlBuilder.build().toString();

        Log.d("URL:", url);

        final Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
