package com.acme.a3csci3130;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.times;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;


import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class Tests {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule(MainActivity.class);

    /**
     * Inputs the values and tests if the method correctly returns to mainactivity
     * @throws InterruptedException
     */
    @Test
    public void aCreateTest() throws InterruptedException {
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.name)).perform(typeText("testName"));
        onView(withId(R.id.email)).perform(typeText("testEmail@email.com"));
        onView(withId(R.id.businessNumber)).perform(typeText("0000000"));
        onView(withId(R.id.primaryBusiness)).perform(typeText("Fisher"));
        onView(withId(R.id.address)).perform(typeText("Testing address"));
        onView(withId(R.id.province)).perform(click());
        onView(withId(R.id.submitButton)).perform(click());
        Thread.sleep(10000);
        intended(hasComponent(MainActivity.class.getName()));
    }

    /**
     * checks if the test can read the email created
     * @throws InterruptedException
     */
    @Test
    public void bReadTest() throws InterruptedException {
        onView(withId(R.id.listView)).perform(click());

        onView(withId(R.id.email)).check(matches(withText("TestEmail@email.com")));
    }

    /**
     * checks if new email can be changed and read
     * @throws InterruptedException
     */
    @Test
    public void cUpdateTest() throws InterruptedException {
        onView(withId(R.id.email)).perform(typeText("newTest@email.com"));
        onView(withId(R.id.updateButton)).perform(click());
        onView(withId(R.id.listView)).perform(click());
        onView(withId(R.id.email)).check(matches(withText("newTest@email.com")));
    }

    /**
     * checks if the data cna be deleted
     * @throws InterruptedException
     */
    @Test
    public void dDeleteTest() throws InterruptedException {
        onView(withId(R.id.deleteButton)).perform(click());
        onView(withId(R.id.listView)).check(doesNotExist());
    }
}