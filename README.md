# fall-19-project-sdk-r
fall-19-project-sdk-r created by GitHub Classroom
-------------------------------------------------------------------------------------------------------------------------------------------
First Iteration: 
	During this iteration we focused our work on the Add Class, Add Event, and View Switching user stories. We decided these were the most important because they were the main structure for our app. The iteration mainly went by smoothly and we all collaborated well. We wanted to work on the Delete Event and Delete Class user stories but we had some trouble trying to save and access the data for Classes and Events so we decided ot just focus on adding the classes and events and having the capability to show them in a list. Also, we weren't able to show any other data for the classes and events other than the name so we will implement this in the next iteration. Finally we had some trouble with creating espresso tests mainly because of the test implementation in gradle. We were able to solve this issue by implementing the correct files.

-------------------------------------------------------------------------------------------------------------------------------------------
Second Iteration:
	During this iteration we mainly focused our work on the Add Event Validation, Add Class Validation, and Remove Event user stories. We wanted to ensure that the events and classes added were made correctly. Also, we used the adapter design pattern in the DataStorageAdpter interface to abstract away the data storage options, whether it uses temporary storage, local non-volitile storage, or server storage. We made sure that there would not be any problems when trying to save or retrieve data from storage too. The main problems we ran into were in testing. Since we had an animation, two of our tests wouldn't pass. Also, we had a long click for the lists which caused problems for our tests. Another problem we had was trying to create a listener on our calender view so that it would generate lists with the events/classes that was on a selected day. We decided that we would work on this in another iteration. Other than these problems, this iteration went by fine and we all worked together well. 
-------------------------------------------------------------------------------------------------------------------------------------------
Third Iteration:
	During this iteration we focused our work on the Remove Class, Settings Page, and Calendar Display user stories. We wanted to make sure that removing classes would work correctly like removing events. We implemented and tested dark mode in the settings page. Also, we added the capability to load events and classes into a list on the calendar page based on a specific day selected on the calendar. The main problems we ran into were during testing. All previous tests passed but the tests for removing classes gave us errors because of Espresso itself. However, classes can be removed correctly in the app. We fixed most of the issues and warnings found with lint testing but some could not be resolved because it would cause our app to not function properly. Other than these issues, this iteration went well.
	
Ignored Lint Warnings (Lint - Performance):
	There are 7 warnings for MainActivity that state that Android context classes are static. We ignored this because we use data from multiple java classes for the MainActivity. The data is Event and Class information used to populate the two lists on the MainActivity. We would be unable to access this data without the fields being static. 

Ignored Java Warnings (Java - Declaration Redundancy):
	We ignored warnings for static fields again for the same reason under Lint. We would be unable to access required data. Under the "Unused Declaration" folder, there are 7 warnings. These warnings state that the View parameter is unused. We tried removing them but it caused our app to crash.
   
Ignored Java Warnings (Java - Naming Conventions):
	These warnings say that the method name was the same as the class name. These warnings are for 6 Espresso tests so we were unable to change the method name because it would cause an error.

Ignored Java Warnings (Java - Probable Bugs):
	These warnings are for @NotNull problems. It says that a method and parameter override @NotNull but when we put @NotNull in both, it caused an error.
	
