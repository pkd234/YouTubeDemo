package com.example.welcome.youtubedemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class YouTubeApi {
    private static final String Url="https://www.googleapis.com/youtube/v3/";
    public static  PostService postService=null;
    private static final String KEY="";

    public static PostService getService(){
        if(postService==null){

            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            postService=retrofit.create(PostService.class);

        }
        return postService;
    }




    public interface PostService
    {



        @GET("search?part=snippet&q=comedy&maxResults=50&key="+KEY)
        Call<Response> getUtubeUrl();


    }

}
