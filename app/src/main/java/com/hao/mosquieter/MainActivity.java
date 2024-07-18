package com.hao.mosquieter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE);
        boolean isNightMode = sharedPreferences.getBoolean("NightMode", false);

        // Apply saved night mode
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

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

    // Method to toggle night mode (if needed for other night mode functionality)
    public void toggleNightMode(View view) {
        // Implement functionality if needed for night mode sound or other features
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
