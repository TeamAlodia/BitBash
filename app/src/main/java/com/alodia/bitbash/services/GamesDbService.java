package com.alodia.bitbash.services;

import android.util.Log;

import com.alodia.bitbash.Constants;
import com.alodia.bitbash.models.Gamelet;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

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

    public void findGamesByPlatform(String query, final Callback callback){
        final OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.URL_GETGAMESBYPLATFORM).newBuilder();
        urlBuilder.addEncodedQueryParameter("platform", query);
        String url = urlBuilder.build().toString();

        Log.d("URL:", url);

        final Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public void findGameById(String query, final Callback callback){
        final OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.URL_GETGAMEBYID).newBuilder();
        urlBuilder.addEncodedQueryParameter("id", query);
        String url = urlBuilder.build().toString();

        Log.d("URL:", url);

        final Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
