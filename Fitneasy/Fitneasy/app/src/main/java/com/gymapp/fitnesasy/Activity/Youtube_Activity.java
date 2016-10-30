package com.gymapp.fitnesasy.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.gymapp.fitnesasy.R;



public class Youtube_Activity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{
    
    YouTubePlayerView youTubePlayerView;
    public static final String YOUTUBE_API_KEY="AIzaSyCt6uuiu6CkDJBGIBmRYnkBEM7ktMsjpWU";
    String linkVideo;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_youtube);

        
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube);

        youTubePlayerView.initialize(YOUTUBE_API_KEY,Youtube_Activity.this);
        linkVideo = getIntent().getStringExtra("videoId");
        







    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if(!b){
            youTubePlayer.cueVideo(linkVideo);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this,1).show();
        }
        else{
            String eror =  String.format("Error ini", youTubeInitializationResult.toString());
            

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            youTubePlayerView.initialize(YOUTUBE_API_KEY,this);
        }
    }
}
