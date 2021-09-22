package com.example.calendarplannerproject;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MultipleClassListedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void multipleClassListedTest() throws InterruptedException {

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction overflowMenuButton2 = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        1),
                                0),
                        isDisplayed()));
        overflowMenuButton2.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Classes"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.addClass),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.nameforClass),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        textInputEditText.perform(replaceText("Class 1"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.classDescription),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                2),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("bottom text"), closeSoftKeyboard());

        ViewInteraction appCompatToggleButton = onView(
                allOf(withId(R.id.toggle_1), withText("M"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.day_picker),
                                        0),
                                2),
                        isDisplayed()));
        appCompatToggleButton.perform(click());

        ViewInteraction appCompatToggleButton2 = onView(
                allOf(withId(R.id.toggle_5), withText("F"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.day_picker),
                                        0),
                                10),
                        isDisplayed()));
        appCompatToggleButton2.perform(click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.dateSelect),
                        isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(R.id.endDateSelect),
                        isDisplayed()));
        appCompatTextView3.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatTextView4 = onView(
                allOf(withId(R.id.timeSelect),
                        isDisplayed()));
        appCompatTextView4.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.submitButton), withText("Submit"),
                        isDisplayed()));
        appCompatButton4.perform(click());


        Thread.sleep(1000);
        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.addClass),
                        isDisplayed()));
        floatingActionButton2.perform(click());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.nameforClass),
                        isDisplayed()));
        textInputEditText3.perform(replaceText("Class 2"), closeSoftKeyboard());

        ViewInteraction textInputEditText29 = onView(
                allOf(withId(R.id.classDescription),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                2),
                        isDisplayed()));
        textInputEditText29.perform(replaceText("top text"), closeSoftKeyboard());

        ViewInteraction appCompatToggleButton9 = onView(
                allOf(withId(R.id.toggle_1), withText("M"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.day_picker),
                                        0),
                                2),
                        isDisplayed()));
        appCompatToggleButton9.perform(click());

        ViewInteraction appCompatToggleButton29 = onView(
                allOf(withId(R.id.toggle_5), withText("F"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.day_picker),
                                        0),
                                10),
                        isDisplayed()));
        appCompatToggleButton29.perform(click());

        ViewInteraction appCompatTextView29 = onView(
                allOf(withId(R.id.dateSelect),
                        isDisplayed()));
        appCompatTextView29.perform(click());

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        isDisplayed()));
        appCompatButton9.perform(click());

        ViewInteraction appCompatTextView39 = onView(
                allOf(withId(R.id.endDateSelect),
                        isDisplayed()));
        appCompatTextView39.perform(click());

        ViewInteraction appCompatButton29 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        isDisplayed()));
        appCompatButton29.perform(click());

        ViewInteraction appCompatTextView4u = onView(
                allOf(withId(R.id.timeSelect),
                        isDisplayed()));
        appCompatTextView4u.perform(click());

        ViewInteraction appCompatButton3u = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        isDisplayed()));
        appCompatButton3u.perform(click());

        ViewInteraction appCompatButton4u = onView(
                allOf(withId(R.id.submitButton), withText("Submit"),
                        isDisplayed()));
        appCompatButton4u.perform(click());



        ViewInteraction listView = onView(
                allOf(withId(R.id.listView),
                        isDisplayed()));
        listView.check(matches(isDisplayed()));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
