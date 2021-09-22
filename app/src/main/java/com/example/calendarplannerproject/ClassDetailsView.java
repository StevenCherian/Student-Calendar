package com.example.calendarplannerproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class ClassDetailsView extends AppCompatActivity {

    private Class classDisplay;

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
        setContentView(R.layout.activity_class_details_view);

        TextView ClassTitleBox = findViewById(R.id.ClassTitleBox);
        TextView ClassDescriptionBox = findViewById(R.id.ClassDescriptionBox);
        TextView ClassStartDateBox = findViewById(R.id.startClassDateBox);
        TextView ClassEndDateBox = findViewById(R.id.endClassDateBox);
        TextView ClassTimeBox = findViewById(R.id.ClassTimeBox);
        TextView ClassDOTW = findViewById(R.id.daysOfWeekBox);



        Intent i = getIntent();
        classDisplay = (Class)i.getSerializableExtra("classInfo");

        ClassTitleBox.setText(Objects.requireNonNull(classDisplay).getName());
        ClassDescriptionBox.setText(classDisplay.getDescription());
        ClassStartDateBox.setText(classDisplay.getDate());
        ClassEndDateBox.setText(classDisplay.getEndDate());
        ClassTimeBox.setText(classDisplay.getTime());
        ClassDOTW.setText(classDisplay.getDaysRepeated());

    }

    public void cancelButton(View view){
        finish();
    }

    public void deleteButton(View view) {
        new TemporaryStorage().deleteClass(classDisplay);
        finish();
    }
}
