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

public class ClassPage extends AppCompatActivity {

    private ClassListAdapter classAdapter;
    private ArrayList<Class> classList;
    private Class classToDisplay;
    private TextView noClassesText;
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
        setContentView(R.layout.activity_class_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TemporaryStorage.loadClassData();

        noClassesText = findViewById(R.id.noClassesTextView);

        FloatingActionButton fab = findViewById(R.id.addClass);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ClassPage.this, addClassInfoPage.class);
                startActivityForResult(i, 10);
            }
        });

        pageRefresh();
    }

    private void displayClassResults(View v) {
        Intent displayClass = new Intent(this, ClassDetailsView.class);
        displayClass.putExtra("classInfo", classToDisplay);
        startActivityForResult(displayClass, 1);
        pageRefresh();
    }

    private void pageRefresh() {
        ListView listView = findViewById(R.id.listView);
        classList = TemporaryStorage.savedClasses;
        classAdapter = new ClassListAdapter(this, classList);
        listView.setAdapter(classAdapter);

        if(classList.size() < 1){
            noClassesText.setVisibility(View.VISIBLE);
        }
        else{
            noClassesText.setVisibility(View.INVISIBLE);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                classToDisplay = classList.get(position);
                displayClassResults(view);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick( AdapterView<?> adapterView, View view, int position, long l) {
                final int itemChosen = position;

                new AlertDialog.Builder(ClassPage.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Delete Class")
                        .setMessage("Are you sure you want to delete this class?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Class e = classList.get(itemChosen);
                                TemporaryStorage.deleteClass(e);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent i) {
        super.onActivityResult(requestCode, resultCode, i);
        pageRefresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                Intent homeIntent = new Intent(ClassPage.this,MainActivity.class);
                startActivity(homeIntent);
                return false;
            case R.id.action_settings:
                Intent settingsIntent = new Intent(ClassPage.this,SettingsPage.class);
                startActivity(settingsIntent);
                return false;
            case R.id.events_page:
                Intent eventsIntent = new Intent(ClassPage.this,EventsPage.class);
                startActivity(eventsIntent);
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
