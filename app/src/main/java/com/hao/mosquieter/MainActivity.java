package com.hao.mosquieter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Method to play noise
    public void playNoise(View view) {
        if (mediaPlayer == null) {
            // Ensure you have a noise file in res/raw
            //mediaPlayer = MediaPlayer.create(this, R.raw.noise);
        }
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            mediaPlayer.seekTo(0);
        } else {
            mediaPlayer.start();
        }
    }

    // Method to open settings activity
    public void openSettings(View view) {
        Intent intent = new Intent(this, SettingsPage.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
