package com.hao.mosquieter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

import java.util.Locale;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;

public class SettingsPage extends AppCompatActivity {

    private static final String TAG = "SettingsPage";
    private Spinner languageSpinner;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE);
        String language = sharedPreferences.getString("Language", "en");
        Log.d(TAG, "Current language: " + language);

        // Apply saved language
        setLocale(language);

        setContentView(R.layout.activity_settings_page);

        languageSpinner = findViewById(R.id.languageSpinner);

        // Initialize SharedPreferences editor
        editor = sharedPreferences.edit();

        // Setup the language spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.language_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);

        // Set the spinner to the current language
        if (language.equals("en")) {
            languageSpinner.setSelection(0);
        } else if (language.equals("tr")) {
            languageSpinner.setSelection(1);
        }

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedLanguage = (position == 0) ? "en" : "tr";
                Log.d(TAG, "Selected language: " + selectedLanguage);
                toggleLanguage(selectedLanguage);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });
    }

    // Method to toggle language
    public void toggleLanguage(String language) {
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
