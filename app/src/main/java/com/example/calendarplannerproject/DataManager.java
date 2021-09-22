package com.example.calendarplannerproject;

// --Commented out by Inspection START (12/11/2019 9:08 AM):
///**
// * Handles saving and loading data
// */
//class DataManager {
//
//    /*
//                Fields
//     */
//    private String eventFileName;
//    // --Commented out by Inspection (12/11/2019 9:08 AM):private String classFileName;
//   // private final File MAIN_FILE;
//
//// --Commented out by Inspection START (12/11/2019 9:05 AM):
////    /*
////                Constructors
////     */
////    public DataManager(String eventFileName, String classFileName, Context context) throws IOException{
////        MAIN_FILE = context.getApplicationContext().getFilesDir();
////        setEventFileName(eventFileName);
////        setClassFileName(classFileName);
////    }
//// --Commented out by Inspection STOP (12/11/2019 9:05 AM)
//
//// --Commented out by Inspection START (12/11/2019 9:05 AM):
////    public DataManager(Context context) {
////        MAIN_FILE = new File(context.getFilesDir(), "events.txt");
//////        Log.d("Main file check", MAIN_FILE.getAbsolutePath());
//////        try {
//////            setEventFileName("events.txt");
//////            setClassFileName("classes.txt");
//////        }
//////        catch(NullPointerException nullPointer){}
//////        catch(Exception e){}
////    }
//// --Commented out by Inspection STOP (12/11/2019 9:05 AM)
//
//    /*
//                Public Methods
//     */
//// --Commented out by Inspection START (12/11/2019 9:06 AM):
////    /**
////     * Gives list of all events stored on device's internal storage
////     *
////     * @return List of all stored events
////     */
////    public ArrayList<Event> getEvents() {
////        //File file = new File(MAIN_FILE, eventFileName);
////        File file = MAIN_FILE;
////
////        ArrayList<Event> events = new ArrayList<>((int) file.length());
////
////        Scanner scan = null;
////        try{
////            scan = new Scanner(file);
////        }
////        catch(IOException ignored){}
////
////        if (scan != null)
////        {
////            while (scan.hasNext())
////            {
////                events.add(new Event(scan.nextLine()));
////            }
////
////            scan.close(); // MAY CAUSE ERROR ON SECOND ATTEMPT!!!
////        }
////        return events;
////    }
//// --Commented out by Inspection STOP (12/11/2019 9:06 AM)
//
//// --Commented out by Inspection START (12/11/2019 9:06 AM):
////    /**
////     * Adds an event to the device's internal storage
////     *
////     * @param event Event to save to device's internal storage
////     */
////    public void saveEvent(Event event) {
////        //File file = new File(MAIN_FILE, eventFileName);
////
////        FileWriter fr = null;
////        try{
////            fr = new FileWriter(MAIN_FILE, true);
////        }
////        catch(IOException ignored){}
////
////        if (fr != null)
////        {
////            PrintWriter pw = new PrintWriter(fr);
////            pw.println(event.saveFormat());
////            pw.close(); // MAY CAUSE ERROR ON SECOND ATTEMPT!!!
////            try {
////                fr.close(); // MAY CAUSE ERROR ON SECOND ATTEMPT!!!
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////        }
////    }
//// --Commented out by Inspection STOP (12/11/2019 9:06 AM)
//
//    public void removeEvent(Event event)
//    {
//        File file = new File(MAIN_FILE, eventFileName);
//
//        ArrayList<Event> events = new ArrayList<>((int) file.length());
//
//        Scanner scan = null;
//        try{
//            scan = new Scanner(file);
//        }
//        catch(IOException ignored){}
//
//        if (scan != null)
//        {
//            while (scan.hasNext())
//                events.add(new Event(scan.nextLine()));
//            scan.close(); // MAY CAUSE ERROR ON SECOND ATTEMPT!!!
//        }
//        throw new UnsupportedOperationException("WIP");
//    }
//
//// --Commented out by Inspection START (12/11/2019 9:06 AM):
////    /**
////     * Gives list of all classes stored on device's internal storage
////     *
////     * @return List of all stored events
////     */
////    public ArrayList<Class> getClasses() {
////        File file = new File(MAIN_FILE, classFileName);
////
////        ArrayList<Class> classes = new ArrayList<>((int) file.length());
////
////        Scanner scan = null;
////        try{
////            scan = new Scanner(file);
////        }
////        catch(IOException ignored){}
////
////        if (scan != null)
////        {
////            while (scan.hasNext())
////                classes.add(new Class(scan.nextLine()));
////            scan.close(); // MAY CAUSE ERROR ON SECOND ATTEMPT!!!
////        }
////        return classes;
////    }
//// --Commented out by Inspection STOP (12/11/2019 9:06 AM)
//
//// --Commented out by Inspection START (12/11/2019 9:06 AM):
////    /**
////     * Adds a class to the device's internal storage
////     *
////     * @param _class Class to save to device's internal storage
////     */
////    public void saveClass(Class _class) {
////        File file = new File(MAIN_FILE, eventFileName);
////
////        FileWriter fr = null;
////        try{
////            fr = new FileWriter(file, true);
////        }
////        catch(IOException ignored){}
////
////        if (fr != null)
////        {
////            PrintWriter pw = new PrintWriter(fr);
////            pw.println(_class.saveFormat());
////            pw.close(); // MAY CAUSE ERROR ON SECOND ATTEMPT!!!
////            try {
////                fr.close(); // MAY CAUSE ERROR ON SECOND ATTEMPT!!!
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////        }
////    }
//// --Commented out by Inspection STOP (12/11/2019 9:06 AM)
//
//// --Commented out by Inspection START (12/11/2019 9:06 AM):
////    /*
////        Mutators
////     */
////    public String getEventFileName() {return eventFileName; }
//// --Commented out by Inspection STOP (12/11/2019 9:06 AM)
//    // --Commented out by Inspection (12/11/2019 9:06 AM):public String getClassFileName() { return classFileName; }
//
//    private void setEventFileName(String fileName) throws IOException{
//        File testFile = new File(MAIN_FILE, fileName);
//        if (testFile.canRead() && testFile.canWrite())
//            eventFileName = fileName;
//        else{
//            throw new IOException("Unable to use " + fileName + " as an event filename, make sure it has an extension.");
//        }
//    }
//    private void setClassFileName(String fileName) throws IOException {
//        File testFile = new File(MAIN_FILE, fileName);
//        if (testFile.canRead() && testFile.canWrite())
//            classFileName = fileName;
//        else
//        {
//            throw new IOException("Unable to use " + fileName + " as a class filename, make sure it has an extension.");
//        }
//    }
//
//}
// --Commented out by Inspection STOP (12/11/2019 9:08 AM)
