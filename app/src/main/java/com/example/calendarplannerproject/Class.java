package com.example.calendarplannerproject;

import java.io.Serializable;

public class Class extends Event implements Serializable {
    // --Commented out by Inspection (12/11/2019 9:25 AM):private final String delim = "123DELIM123";

    private final String teacher;
    private final String location;
    private String daysRepeated;
    private String endDate;

    // --Commented out by Inspection (12/11/2019 9:04 AM):private String getTeacher() {return teacher;}
    // --Commented out by Inspection (12/11/2019 9:25 AM):private void setTeacher(String teacher) {this.teacher = teacher;}
    // --Commented out by Inspection (12/11/2019 9:04 AM):private String getLocation() {return location;}
    // --Commented out by Inspection (12/11/2019 9:25 AM):private void setLocation(String location) {this.location = location;}

    public Class() {
        teacher ="";
        location = "";
    }

// --Commented out by Inspection START (12/11/2019 9:24 AM):
//    public Class(String name, String desc, String time, String date) {
//        this.name = name;
//        this.description = desc;
//        this.time = time;
//        this.date = date;
//    }
// --Commented out by Inspection STOP (12/11/2019 9:24 AM)

// --Commented out by Inspection START (12/11/2019 9:23 AM):
//    public Class(String saveData) {
//        String[] data = saveData.split(delim);
//        setTeacher(data[0]);
//        setLocation(data[1]);
//    }
// --Commented out by Inspection STOP (12/11/2019 9:23 AM)

    public void setDaysRepeated(String daysRepeated) {
        this.daysRepeated = daysRepeated;

    }

    public String getDaysRepeated(){
        return daysRepeated;
    }

    public void setEndDate(String endDate){
        this.endDate = endDate;
    }

    public String getEndDate(){
        return endDate;
    }

// --Commented out by Inspection START (12/11/2019 9:23 AM):
//    public String saveFormat() {
//        // Teacher,Location
//        return teacher + delim + location;
//    }
// --Commented out by Inspection STOP (12/11/2019 9:23 AM)

    @Override
    public boolean equals(Object o) {
        if(o instanceof Class) {
            Class c = (Class) o;
            return c.location.equals(location) &&
                    c.teacher.equals(teacher);
        } else {
            return false;
        }
    }
}