package com.hao.mosquieter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private ImageButton pauseButton, settingsButton, nightButton;
    private TextView pauseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pauseButton = findViewById(R.id.button);
        settingsButton = findViewById(R.id.button_open_settings);
        nightButton = findViewById(R.id.button3);
        pauseText = findViewById(R.id.pause_text);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pauseText.getText().toString().equals("Click to continue.")) {
                    pauseButton.invalidate();
                    pauseButton.setImageResource(R.drawable.continue_icon); // Yeni resmi ayarla
                    pauseText.setText("Click to pause.");
                    // add method to play noise
                } else {
                    pauseButton.invalidate();
                    pauseButton.setImageResource(R.drawable.pause_icon); // Yeni resmi ayarla
                    pauseText.setText("Click to continue.");
                }
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SettingsPage.class));
            }
        });


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
