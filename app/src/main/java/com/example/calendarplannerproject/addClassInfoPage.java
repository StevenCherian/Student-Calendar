package com.example.calendarplannerproject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import ca.antonious.materialdaypicker.MaterialDayPicker;

public class addClassInfoPage extends AppCompatActivity {

    private static final String TAG = "addClassInfoPage";
    private TextView displayDate;
    private TextView displayEndDate;


    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private DatePickerDialog.OnDateSetListener onEndDateSetListener;


    //for timePicker
    private TextView displayTime;
    private TimePickerDialog timePickerDialog;
    private Calendar timeCalendar;

    private int currentHour;
    private int currentMinute;
    private String amPm;

    private TextInputEditText name;
    private TextInputEditText description;

    private TextView date;
    private TextView endDate;
    private TextView time;
    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";

    //DOTW
    private String daysSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);

        if(useDarkTheme){
            setTheme(R.style.AppTheme_Dark);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class_info_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //DOTW Selected

        final MaterialDayPicker materialDayPicker = findViewById(R.id.day_picker);
      //  final ArrayList<String> repeatedDays = new ArrayList<String>();
      //   List<MaterialDayPicker.Weekday> repeatedDays = new ArrayList<>();

        materialDayPicker.setDaySelectionChangedListener(new MaterialDayPicker.DaySelectionChangedListener() {
            @Override
            public void onDaySelectionChanged(List<MaterialDayPicker.Weekday> selectedDays) {

               daysSelected = selectedDays.toString();
               // appendLog(String.format("[DaySelectionChangedListener]%s", selectedDays.toString()));
            }
        });



     /*   materialDayPicker.setDayPressedListener(new MaterialDayPicker.DayPressedListener() {
            @Override
            public void onDayPressed( MaterialDayPicker.Weekday weekday, boolean isSelected) {
                repeatedDays.add(weekday.toString());
            }
        }); */

        //List<MaterialDayPicker.Weekday> repeatedDays = materialDayPicker.getSelectedDays();


        displayDate = findViewById(R.id.dateSelect);
        displayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(addClassInfoPage.this,
                        android.R.style.Theme_DeviceDefault_Dialog_Alert,onDateSetListener, year, month, day);
                dialog.show();

            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet:  m/dd/yyyy: " + month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year;
                displayDate.setText(date);
            }
        };


        ///END DATE
        displayEndDate = findViewById(R.id.endDateSelect);
        displayEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(addClassInfoPage.this,
                        android.R.style.Theme_DeviceDefault_Dialog_Alert,onEndDateSetListener, year, month, day);
                dialog.show();

            }
        });

        onEndDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet:  m/dd/yyyy: " + month +"/" + day + "/" + year);
                String date = month + "/" + day + "/" + year;
                displayEndDate.setText(date);
            }
        };

        //TIMEPICK

        displayTime = findViewById(R.id.timeSelect);
        displayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context wrapper = new ContextThemeWrapper(addClassInfoPage.this, R.style.AppTheme);
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
                Class newClass = new Class();

                name = findViewById(R.id.nameforClass);
                description = findViewById(R.id.classDescription);
                date = findViewById(R.id.dateSelect);
                time = findViewById(R.id.timeSelect);
                endDate = findViewById(R.id.endDateSelect);


                String name_s = Objects.requireNonNull(name.getText()).toString();

                if (name_s.equals("")){
                    Toast.makeText(getApplicationContext(), "Class must have a name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(time.getText().equals("")){
                    Toast.makeText(getApplicationContext(), "Class must have a time!", Toast.LENGTH_SHORT).show();
                    return;
                }

                newClass.setName(name_s);
                newClass.setDescription(Objects.requireNonNull(description.getText()).toString());
                newClass.setDate(date.getText().toString());
                newClass.setTime(time.getText().toString());
                newClass.setEndDate(endDate.getText().toString());
                newClass.setDaysRepeated(daysSelected);

                //Implement save class

                TemporaryStorage.saveClass(newClass);
                finish();
            }
        });
    }

    public void cancelButton(View view){
        finish();
    }
}


