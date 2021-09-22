package com.example.calendarplannerproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class EventDetailsView extends AppCompatActivity {

    private String name, desc, date, time;

    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);

        if(useDarkTheme){
            setTheme(R.style.AppTheme_Dark);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details_view);

        TextView EventTitleBox = findViewById(R.id.EventTitleBox);
        TextView EventDescriptionBox = findViewById(R.id.EventDescriptionBox);
        TextView EventDateBox = findViewById(R.id.EventDateBox);
        TextView EventTimeBox = findViewById(R.id.EventTimeBox);

        Intent i = getIntent();
        Event event = (Event)i.getSerializableExtra("eventInfo");

        name = Objects.requireNonNull(event).getName();
        desc = event.getDescription();
        date = event.getDate();
        time = event.getTime();

        EventTitleBox.setText(event.getName());
        EventDescriptionBox.setText(event.getDescription());
        EventDateBox.setText(event.getDate());
        EventTimeBox.setText(event.getTime());
    }

    public void cancelButton(View view){
        finish();
    }

    public void deleteButton(View view) {
        Event e = new Event(name, desc, time, date);
        new TemporaryStorage().deleteEvent(e);
        finish();
    }
}
