package com.alodia.bitbash.services;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.alodia.bitbash.Constants;
import com.alodia.bitbash.R;
import com.alodia.bitbash.models.Game;
import com.alodia.bitbash.models.Gamelet;
import com.alodia.bitbash.ui.activities.CreateBashActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
    private Context mContext;
    private Dialog mDialog;

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

    public Game processResultById(Response response, String id, final Context context) {
        final Game game = new Game();
        mContext = context;

        game.setGameId(id);

        JSONObject jsonObj = null;
        try {
            jsonObj = XML.toJSONObject(response.body().string());

            Log.v("::::", jsonObj.toString());
            if(jsonObj.getJSONObject("Data").getJSONObject("Game").has("GameTitle")){
                game.setName( jsonObj.getJSONObject("Data").getJSONObject("Game").getString("GameTitle"));
            }

            if (jsonObj.getJSONObject("Data").getJSONObject("Game").has("Players")) {
                game.setNumberOfPlayers(jsonObj.getJSONObject("Data").getJSONObject("Game").getString("Players"));
            }

            if(jsonObj.getJSONObject("Data").getJSONObject("Game").has("Publisher")){
                game.setPublisher(jsonObj.getJSONObject("Data").getJSONObject("Game").getString("Publisher"));
            }

            if(jsonObj.getJSONObject("Data").getJSONObject("Game").has("Developer")){
                game.setDeveloper(jsonObj.getJSONObject("Data").getJSONObject("Game").getString("Developer"));
            }

            if(jsonObj.getJSONObject("Data").getJSONObject("Game").has("ReleaseDate")){
                game.setReleaseDate(jsonObj.getJSONObject("Data").getJSONObject("Game").getString("ReleaseDate"));
            }

            if(jsonObj.getJSONObject("Data").getJSONObject("Game").has("Co-op")){
                game.setHasCoop(jsonObj.getJSONObject("Data").getJSONObject("Game").getString("Co-op"));
            }

            if(jsonObj.getJSONObject("Data").getJSONObject("Game").has("Overview")){
                game.setOverview(jsonObj.getJSONObject("Data").getJSONObject("Game").getString("Overview").replace("*", ".").replace("\r", "").replace("\n", ""));
            }

            if(jsonObj.getJSONObject("Data").getJSONObject("Game").getJSONObject("Images").has("boxart")){
                if(jsonObj.getJSONObject("Data").getJSONObject("Game").getJSONObject("Images").getString("boxart").contains("[")){
                    Log.v("::Boxart::", "Array");
                    for (int i = 0; i < jsonObj.getJSONObject("Data").getJSONObject("Game").getJSONObject("Images").getJSONArray("boxart").length(); i++){
                        if(jsonObj.getJSONObject("Data").getJSONObject("Game").getJSONObject("Images").getJSONArray("boxart").getJSONObject(i).getString("side").equals("front")){
                            game.setBoxArt("http://thegamesdb.net/banners/_gameviewcache/" + jsonObj.getJSONObject("Data").getJSONObject("Game").getJSONObject("Images").getJSONArray("boxart").getJSONObject(i).getString("content"));
                        }
                    }
                }else{
                    Log.v("::::", "object");
                    game.setBoxArt("http://thegamesdb.net/banners/_gameviewcache/" + jsonObj.getJSONObject("Data").getJSONObject("Game").getJSONObject("Images").getJSONObject("boxart").getString("content"));
                }

            }else{
                game.setBoxArt("https://image.freepik.com/free-icon/question-mark_318-52837.jpg");
            }

//            if(jsonObj.getJSONObject("Data").getJSONObject("Game").getJSONObject("Images").has("screenshot")){
//                if(jsonObj.getJSONObject("Data").getJSONObject("Game").getJSONObject("Images").getString("screenshot").contains("[")){
//                    Log.v("::Screenshots::", "Array");
//                    for (int i = 0; i < jsonObj.getJSONObject("Data").getJSONObject("Game").getJSONObject("Images").getJSONArray("screenshot").length(); i++){
//                        String catcher = "http://thegamesdb.net/banners/_gameviewcache/" + jsonObj.getJSONObject("Data").getJSONObject("Game").getJSONObject("Images").getJSONArray("screenshot").getJSONObject(i).getJSONObject("original").getString("content");
//                        screenshots.add(catcher);
//                        Log.v("Screenshot", screenshots.get(i));
//
//                    }
//                }
//            }else{
//                screenshots.add("https://image.freepik.com/free-icon/question-mark_318-52837.jpg");
//            }

            //TODO: Sort out genre parsing
//            if(jsonObj.getJSONObject("Data").getJSONObject("Game").has("Genres")){
//                String catcher = jsonObj.getJSONObject("Data").getJSONObject("Game").getJSONObject("Genres").getString("genre").replace("[", "").replace("\"", "").replace("]", "");
//                Log.v("::Genres::", catcher);
//            }else{
//                genres.add("?");
//            }
            if(context.getClass() == CreateBashActivity.class){
                ((CreateBashActivity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        executeDialog(game);
                    }
                });
            }

        } catch (JSONException e) {
            Log.e("JSON exception", e.getMessage());
            e.printStackTrace();
        } catch (IOException e){

        }

        return game;
    }

    public void executeDialog(Game game){
        final Dialog dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.dialog_game);

        ImageView imageView_BoxArt = (ImageView)dialog.findViewById(R.id.imageView_BoxArt);
        TextView textView_Overview = (TextView)dialog.findViewById(R.id.textView_Overview);
        TextView textView_Developer = (TextView)dialog.findViewById(R.id.textView_Developer);
        TextView textView_DeveloperDetail = (TextView)dialog.findViewById(R.id.textView_DeveloperDetail);
        TextView textView_PublisherDetail = (TextView)dialog.findViewById(R.id.textView_PublisherDetail);
        TextView textView_Publisher = (TextView)dialog.findViewById(R.id.textView_Publisher);
        TextView textView_NumberOfPlayers = (TextView)dialog.findViewById(R.id.textView_NumberOfPlayers);
        TextView textView_NumberOfPlayersDetail = (TextView)dialog.findViewById(R.id.textView_NumberOfPlayersDetail);
        TextView textView_HasCoop = (TextView)dialog.findViewById(R.id.textView_HasCoop);
        TextView textView_HasCoopDetail = (TextView)dialog.findViewById(R.id.textView_HasCoopDetail);
        TextView textView_ReleaseDate = (TextView)dialog.findViewById(R.id.textView_ReleaseDate);
        TextView textView_ReleaseDateDetail = (TextView)dialog.findViewById(R.id.textView_ReleaseDateDetail);

        Typeface PlayRegular = Typeface.createFromAsset(mContext.getAssets(), "fonts/Play-Regular.ttf");
        Typeface PlayBold = Typeface.createFromAsset(mContext.getAssets(), "fonts/Play-Bold.ttf");

        Picasso.with(mContext.getApplicationContext()).load(game.getBoxArt()).into(imageView_BoxArt);

        textView_Overview.setTypeface(PlayRegular);
        textView_DeveloperDetail.setTypeface(PlayRegular);
        textView_PublisherDetail.setTypeface(PlayRegular);
        textView_NumberOfPlayersDetail.setTypeface(PlayRegular);
        textView_HasCoopDetail.setTypeface(PlayRegular);
        textView_ReleaseDateDetail.setTypeface(PlayRegular);


        textView_Publisher.setTypeface(PlayBold);
        textView_Developer.setTypeface(PlayBold);
        textView_NumberOfPlayers.setTypeface(PlayBold);
        textView_HasCoop.setTypeface(PlayBold);
        textView_ReleaseDate.setTypeface(PlayBold);

        textView_Overview.setText(game.getOverview());
        textView_PublisherDetail.setText(game.getPublisher());
        textView_DeveloperDetail.setText(game.getDeveloper());
        textView_NumberOfPlayersDetail.setText(game.getNumberOfPlayers());
        textView_HasCoopDetail.setText(game.getHasCoop());
        textView_ReleaseDateDetail.setText(game.getReleaseDate());

        dialog.show();
    }
}
