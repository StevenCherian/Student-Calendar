package com.example.calendarplannerproject;


import java.io.Serializable;

public class Event implements Serializable {
    // --Commented out by Inspection (12/11/2019 9:25 AM):private static final String delim  = "!DELIM!";

    private String name;
    private String description;
    private String time;
    private String date; // mm/dd/yyyy

    //just a test push

    public Event() {
        name ="";
        description = "";
    }

    public Event(String name, String desc, String time, String date) {
        this.name = name;
        this.description = desc;
        this.time = time;
        this.date = date;
    }


// --Commented out by Inspection START (12/11/2019 9:21 AM):
//    public Event (String eventData) {
//        String[] data = eventData.split(delim);
//        setName(data[0]);
//        setDescription(data[1]);
//        // setTime(data[2]);
//        // setDate(data[3]);
//    }
// --Commented out by Inspection STOP (12/11/2019 9:21 AM)

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String toString() {
        return "";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Event) {
            Event e = (Event) o;
            return e.date.equals(date) &&
                    e.description.equals(description) &&
                    e.name.equals(name) &&
                    e.time.equals(time);
        } else {
            return false;
        }
    }

// --Commented out by Inspection START (12/11/2019 9:08 AM):
//    /**
//     * Gives the event's data in a savable format
//     * @return String in the format to save all it's information
//     */
//    public String saveFormat() {
//        // name,description,time,date
//        return name + delim + description + delim + time + delim + date;
//    }
// --Commented out by Inspection STOP (12/11/2019 9:08 AM)
}
