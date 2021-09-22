package com.example.calendarplannerproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

//All credit for creating animations goes to https://www.youtube.com/watch?v=Cys_i-6Pu-o

public class MainActivity extends AppCompatActivity {

    public static Context context;

    private static ListView eventListView;
    private static ListView classListView;

    private static EventListAdapter eventAdapter;
    private static ArrayList<Event> eventList;


    private static ClassListAdapter classAdapter;
    private static ArrayList<Class> classList;

    private FloatingActionButton mainFab;
    private FloatingActionButton addClassFab;
    private FloatingActionButton addEventFab;
    private Animation fabOpen;
    private Animation fabClose;
    private Animation rotateForward;
    private Animation rotateBackward;
    private boolean isOpen;

    private static TextView noEventsText;
    private static TextView noClassesText;
    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";

    private static ArrayList<Event> eventListForDaySelected = new ArrayList<>();
    private static ArrayList<Class> classListForDaySelected = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);

        if(useDarkTheme){
            setTheme(R.style.AppTheme_Dark);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mActionBarToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Student Calendar");

        noEventsText = findViewById(R.id.NoEventsText);
        noClassesText = findViewById(R.id.NoClassesText);

        context = this;

        TemporaryStorage.loadEventData();
        TemporaryStorage.loadClassData();

        eventListView = findViewById(R.id.eventListView);
        eventList = TemporaryStorage.savedEvents;
        eventAdapter = new EventListAdapter(this, eventListForDaySelected);
        eventListView.setAdapter(eventAdapter);

        classListView = findViewById(R.id.classListView);
        classList = TemporaryStorage.savedClasses;
        classAdapter = new ClassListAdapter(this, classList);
        classListView.setAdapter(classAdapter);

        mainFab = findViewById(R.id.mainFab);
        addClassFab = findViewById(R.id.addClass);
        addEventFab = findViewById(R.id.addEvent);

        fabOpen = AnimationUtils.loadAnimation(this,R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this,R.anim.fab_close);

        rotateForward = AnimationUtils.loadAnimation(this,R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(this,R.anim.rotate_backward);

        mainFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               animateFab();
            }
        });

        addClassFab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                animateFab();
                Intent openAddClass = new Intent(MainActivity.this,addClassInfoPage.class);
                startActivity(openAddClass);
            }
        });

        addEventFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                animateFab();
                Intent openAddEvent = new Intent(MainActivity.this,addEventInfoPage.class);
                startActivity(openAddEvent);
            }
        });

        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {

                String daySelected = month+1 + "/" + day + "/" + year;
                eventList = TemporaryStorage.savedEvents;


                for(int i =0; i<eventList.size(); i++){
                    if(eventList.get(i).getDate().equals(daySelected)){
                        eventListForDaySelected.add(eventList.get(i));
                        eventListRefresh();
                        TemporaryStorage.loadEventData();
                    }
                }

                eventListRefresh();
                eventListForDaySelected = new ArrayList<>();

                for(int i =0; i<classList.size(); i++){
                    if(classList.get(i).getDate().equals(daySelected)){
                        classListForDaySelected.add(classList.get(i));
                        TemporaryStorage.loadClassData();
                        classListRefresh();
                    }
                }

                classListRefresh();
                classListForDaySelected = new ArrayList<>();
            }
        });

        eventListRefresh();
        classListRefresh();
    }

     public static void eventListRefresh() {
        eventList = TemporaryStorage.savedEvents;
        eventAdapter = new EventListAdapter(context, eventListForDaySelected);
        eventListView.setAdapter(eventAdapter);

        if(eventListForDaySelected.size() < 1){
            noEventsText.setVisibility(View.VISIBLE);

        }
        else{

            noEventsText.setVisibility(View.INVISIBLE);
        }

       eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event eventToDisplay = eventAdapter.getItem(position);
                displayEventResults(context, eventToDisplay);
            }
       });

       eventListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

           @Override
           public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
               final int itemChosen = position;

               new AlertDialog.Builder(context)
                       .setIcon(android.R.drawable.ic_delete)
                       .setTitle("Delete Event")
                       .setMessage("Are you sure you want to delete this event?")
                       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                   Event e = eventAdapter.getItem(itemChosen);
                                   TemporaryStorage.deleteEvent(e);
                                   TemporaryStorage.loadEventData();
                                   eventAdapter.notifyDataSetChanged();
                               }
                        })
                       .setNegativeButton("No", null)
                       .show();
                return true;
           }
       });
    }

    public static void classListRefresh() {
        classList = TemporaryStorage.savedClasses;
        classAdapter = new ClassListAdapter(context, classListForDaySelected);
        classListView.setAdapter(classAdapter);

        if(classListForDaySelected.size() < 1){
            noClassesText.setVisibility(View.VISIBLE);
        }
        else{
            noClassesText.setVisibility(View.INVISIBLE);
        }

        classListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Class classToDisplay = classAdapter.getItem(position);
                displayClassResults(context, classToDisplay);
            }
        });

        classListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick( AdapterView<?> adapterView, View view, int position, long l) {
                final int classChosen = position;

                new AlertDialog.Builder((context))
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Delete Class")
                        .setMessage("Are you sure you want to delete this class?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Class chosenClass = classAdapter.getItem(classChosen);
                                TemporaryStorage.deleteClass(chosenClass);
                                TemporaryStorage.loadClassData();
                                classAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });
    }

    private static void displayEventResults(Context c, Event e) {
        Intent displayEvent = new Intent(c, EventDetailsView.class);
        displayEvent.putExtra("eventInfo", e);
        c.startActivity(displayEvent);
    }

    private static void displayClassResults(Context c, Class cls) {
        Intent displayClass = new Intent(c, ClassDetailsView.class);
        displayClass.putExtra("classInfo", cls);
        c.startActivity(displayClass);
    }

    private void animateFab() {
        if(isOpen){
            mainFab.startAnimation(rotateForward);
            addEventFab.startAnimation(fabClose);
            addClassFab.startAnimation(fabClose);
            addEventFab.setClickable(false);
            addClassFab.setClickable(false);
            isOpen = false;
        } else {
            mainFab.startAnimation(rotateBackward);
            addEventFab.startAnimation(fabOpen);
            addClassFab.startAnimation(fabOpen);
            addEventFab.setClickable(true);
            addClassFab.setClickable(true);
            isOpen = true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent i) {
        super.onActivityResult(requestCode, resultCode, i);
       // eventListRefresh();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch(item.getItemId()) {
            case R.id.action_settings:
                Intent settingsIntent = new Intent(MainActivity.this,SettingsPage.class);
                startActivity(settingsIntent);
                return false;
            case R.id.events_page:
                Intent eventsIntent = new Intent(MainActivity.this,EventsPage.class);
                startActivity(eventsIntent);
                return false;
            case R.id.classes_page:
                Intent classesIntent = new Intent(MainActivity.this,ClassPage.class);
                startActivity(classesIntent);
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
