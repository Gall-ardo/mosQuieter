package com.hao.mosquieter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.Locale;

public class SettingsPage extends AppCompatActivity {

    private Switch nightModeSwitch;
    private Switch languageSwitch;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE);
        boolean isNightMode = sharedPreferences.getBoolean("NightMode", false);
        String language = sharedPreferences.getString("Language", "en");

        // Apply saved night mode
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        // Apply saved language
        setLocale(language);

        setContentView(R.layout.activity_settings_page);

        nightModeSwitch = findViewById(R.id.switch1);
        languageSwitch = findViewById(R.id.switch3);

        nightModeSwitch.setChecked(isNightMode);
        languageSwitch.setChecked(language.equals("tr"));

        // Initialize SharedPreferences editor
        editor = sharedPreferences.edit();

        // Set listeners for the switches
        nightModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                toggleNightMode(isChecked);
            }
        });

        languageSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                toggleLanguage(isChecked);
            }
        });
    }

    // Method to toggle night mode
    public void toggleNightMode(boolean isChecked) {
        boolean currentNightMode = (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES;
        if (isChecked != currentNightMode) {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }

            // Save night mode setting to SharedPreferences
            editor.putBoolean("NightMode", isChecked);
            editor.apply();

            // Restart the activity to apply changes
            recreate();
        }
    }

    // Method to toggle language
    public void toggleLanguage(boolean isChecked) {
        String language = isChecked ? "tr" : "en";
        setLocale(language);

        // Save language setting to SharedPreferences
        editor.putString("Language", language);
        editor.apply();

        // Restart the activity to apply changes
        recreate();
    }

    private void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }
}
