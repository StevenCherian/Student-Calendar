package com.example.calendarplannerproject;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

class TemporaryStorage {

    public static ArrayList<Event> savedEvents;
    public static ArrayList<Class> savedClasses;

    private static final SharedPreferences mySavedEvents = MainActivity.context.getSharedPreferences("SavedEvents", MODE_PRIVATE);
    private static final SharedPreferences mySavedClasses = MainActivity.context.getSharedPreferences("SavedClasses", MODE_PRIVATE);

// --Commented out by Inspection START (12/10/2019 10:57 PM):
//    public ArrayList<Event> getEvents() {
//        ArrayList<Event> copyOfEvents = new ArrayList<>(savedEvents.size());
//        copyOfEvents.addAll(savedEvents);
//
//        return copyOfEvents;
//    }
// --Commented out by Inspection STOP (12/10/2019 10:57 PM)

    public static void saveEvent(Event e) {
        savedEvents.add(e);
        saveEventData();
        MainActivity.eventListRefresh();
    }

    public static void deleteEvent(Event e) {
        savedEvents.remove(e);
        saveEventData();
        MainActivity.eventListRefresh();
    }

// --Commented out by Inspection START (12/10/2019 10:57 PM):
//    public ArrayList<Class> getClasses() {
//        ArrayList<Class> copyOfClasses = new ArrayList<>(savedClasses.size());
//        copyOfClasses.addAll(savedClasses);
//        return copyOfClasses;
//    }
// --Commented out by Inspection STOP (12/10/2019 10:57 PM)

    public static void saveClass(Class c) {
        savedClasses.add(c);
        saveClassData();
        MainActivity.classListRefresh();
    }

    public static void deleteClass(Class c) {
        savedClasses.remove(c);
        saveClassData();
        MainActivity.classListRefresh();
    }

    private static void saveEventData() {
        SharedPreferences.Editor editor = mySavedEvents.edit();
        Gson gson = new Gson();
        String eventJson = gson.toJson(savedEvents);
        editor.putString("Saved event", eventJson);
        editor.apply();
    }

    public static void loadEventData() {
        SharedPreferences sharedPreferences = MainActivity.context.getSharedPreferences("SavedEvents", MODE_PRIVATE);
        Gson gson = new Gson();
        String eventJson = sharedPreferences.getString("Saved event", null);
        Type type = new TypeToken<ArrayList<Event>>(){}.getType();
        savedEvents = gson.fromJson(eventJson, type);

        if(savedEvents == null){
            savedEvents = new ArrayList<>();
        }
    }

    private static void saveClassData() {
        SharedPreferences.Editor editor = mySavedClasses.edit();
        Gson gson = new Gson();
        String classJson = gson.toJson(savedClasses);
        editor.putString("Saved class", classJson);
        editor.apply();
    }

    public static void loadClassData() {
        SharedPreferences sharedPreferences = MainActivity.context.getSharedPreferences("SavedClasses", MODE_PRIVATE);
        Gson gson = new Gson();
        String classJson = sharedPreferences.getString("Saved class", null);
        Type type = new TypeToken<ArrayList<Class>>(){}.getType();
        savedClasses = gson.fromJson(classJson, type);

        if(savedClasses == null){
            savedClasses = new ArrayList<>();
        }
    }
}
