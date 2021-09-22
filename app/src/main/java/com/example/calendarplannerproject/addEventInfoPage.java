package com.example.calendarplannerproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import android.os.Bundle;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Objects;

public class addEventInfoPage extends AppCompatActivity {

    //for datePicker
    private static final String TAG = "addEventInfoPage";
    private TextView displayDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    //for timePicker
    private TextView displayTime;
    private TimePickerDialog timePickerDialog;
    private Calendar timeCalendar;

    private int currentHour;
    private int currentMinute;
    private String amPm;

    private TextInputEditText name;
    private TextInputEditText description;
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
        setContentView(R.layout.activity_add_event_info_page);
        name = findViewById(R.id.nameforClass);
        description = findViewById(R.id.classDescription);
        displayDate = findViewById(R.id.dateSelect);
        displayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(addEventInfoPage.this,
                        android.R.style.Theme_DeviceDefault_Dialog_Alert,onDateSetListener, year, month, day);
                dialog.show();

            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet:  m/dd/yyyy: " + month +"/" + day + "/" + year);
                String date = month +"/" + day + "/" + year;
                displayDate.setText(date);
            }
        };

        displayTime = findViewById(R.id.timeSelect);
        displayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context wrapper = new ContextThemeWrapper(addEventInfoPage.this, R.style.AppTheme);
                timeCalendar = Calendar.getInstance();
                currentHour = timeCalendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = timeCalendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(wrapper, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12){
                            amPm = "PM";
                        }else{
                            amPm = "AM";
                        }

                        if(minutes < 10){
                            String time = hourOfDay%12 + ":0" + minutes + amPm;
                            displayTime.setText(time);
                        }else{
                            String time = hourOfDay%12 + ":" + minutes + amPm;
                            displayTime.setText(time);
                        }
                    }
                }, currentHour, currentMinute, false );
                timePickerDialog.show();
            }
        });

        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event newEvent = new Event();

                String name_s = Objects.requireNonNull(name.getText()).toString();
                String desc_s = Objects.requireNonNull(description.getText()).toString();
                String date_s = displayDate.getText().toString();
                String time_s = displayTime.getText().toString();

                if(name_s.equals("")){
                    Toast.makeText(getApplicationContext(), "Event must have a name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(date_s.equals("")){
                    Toast.makeText(getApplicationContext(), "Event must have a date!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(time_s.equals("")){
                    Toast.makeText(getApplicationContext(), "Event must have a time!", Toast.LENGTH_SHORT).show();
                    return;
                }

                newEvent.setName(name_s);
                newEvent.setDescription(desc_s);
                newEvent.setTime(time_s);
                newEvent.setDate(date_s);


                TemporaryStorage.saveEvent(newEvent);

       /*         if(TemporaryStorage.savedEvents.get(0).getName().equals("No Events")){
                    TemporaryStorage.deleteEvent(TemporaryStorage.savedEvents.get(0));
                }*/
                finish();
            }
        });
    }

    public void cancelButton(View view){
        finish();
    }
}
