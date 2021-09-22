package com.example.calendarplannerproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class EventsPage extends AppCompatActivity {

    private EventListAdapter eventAdapter;
    private Event eventToDisplay;
    private TextView noEventsText;
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
        setContentView(R.layout.activity_events_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TemporaryStorage.loadEventData();
        noEventsText = findViewById(R.id.noEventsTextView);

        FloatingActionButton fab = findViewById(R.id.addEvent);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EventsPage.this, addEventInfoPage.class);
                startActivityForResult(i, 10);
            }
        });

        pageRefresh();
    }

    private void displayEventResults() {
        Intent displayEvent = new Intent(this, EventDetailsView.class);
        displayEvent.putExtra("eventInfo", eventToDisplay);
        startActivityForResult(displayEvent, 1);
        pageRefresh();
    }

    private void pageRefresh() {
        ListView listView = findViewById(R.id.listView);
        ArrayList<Event> eventList = TemporaryStorage.savedEvents;
        eventAdapter = new EventListAdapter(this, eventList);
        listView.setAdapter(eventAdapter);

        if(eventList.size() < 1){
            noEventsText.setVisibility(View.VISIBLE);
        }
        else{
            noEventsText.setVisibility(View.INVISIBLE);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                eventToDisplay = eventAdapter.getItem(position);
                displayEventResults();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick( AdapterView<?> adapterView, View view, int position, long l) {
                final int itemChosen = position;

                new AlertDialog.Builder(EventsPage.this)
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent i) {
        super.onActivityResult(requestCode, resultCode, i);
        pageRefresh();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                Intent homeIntent = new Intent(EventsPage.this,MainActivity.class);
                startActivity(homeIntent);
                return false;
            case R.id.action_settings:
                Intent settingsIntent = new Intent(EventsPage.this,SettingsPage.class);
                startActivity(settingsIntent);
                return false;
            case R.id.classes_page:
                Intent classesIntent = new Intent(EventsPage.this,ClassPage.class);
                startActivity(classesIntent);
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
