package com.example.welcome.youtubedemo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.perfectlib.ToastGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class YoutubeData extends AppCompatActivity {

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_data);
        recyclerView=findViewById(R.id.root_recycle);
        if(isConnected()) {
            getData();
        }
        else {
            ToastGenerator.errorToast(getApplicationContext(),"Connection Lost !");
        }



    }

    private void getData() {
        final Call<Response> responseCall=YouTubeApi.getService().getUtubeUrl();
        responseCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                List<ItemsItem> data=response.body().getItems();
                final LinearLayoutManager manager=new LinearLayoutManager(YoutubeData.this);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(new YoutubeAdapter(YoutubeData.this,data));


            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.d("errorMSG", t.getMessage());

            }
        });

    }
    boolean isConnected(){
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;
        return connected;
    }
}

