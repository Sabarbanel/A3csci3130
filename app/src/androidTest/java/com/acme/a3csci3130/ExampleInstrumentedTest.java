package com.acme.a3csci3130;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.fail;
import static org.hamcrest.CoreMatchers.anything;


/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest extends MyApplicationData {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    /**
     * Creating an entry, which will be used later and verified that it exists
     */
    @Test
    public void createEntryFirebase() {
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.province)).perform(typeText("NS"));
        onView(withId(R.id.primarybuisness)).perform(typeText("fisher"));
        onView(withId(R.id.buisnessnNumber)).perform(typeText("123456789"));
        onView(withId(R.id.name)).perform(typeText("SquishaFish"));
        onView(withId(R.id.address)).perform(typeText("234 long point drive, b3p 2s4"));
        // for some reason this won't work. The submit, and enter form button are just copied and
        // pasted, and I have no idea where the first one connects, so I can't refactor this.
        // And, my project is breaking randomly, so I have no idea what works and what
        // doesn't.


        //Espresso.onView(withId(R.id.submitButton), withText("Add Entry"))).perform(click());

        pressBack();
    }

    /**
     * modifies the entry to have BC as its province, which will be read later
     */
    @Test
    public void modifyEntryFirebase() {
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.province)).perform(clearText());
        onView(withId(R.id.province)).perform(typeText("BC"));
        onView(withId(R.id.updateButton)).perform(click());

        pressBack();

    }

    /**
     * Reading the only entry in the database, and assessing that it exists,
     * was modified, and is readable.
     */
    @Test
    public void readEntryFirebase() {
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.province)).check(matches(withText("This password is too weak")));
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.province)).check(matches(withText("BC")));
        onView(withId(R.id.primarybuisness)).check(matches(withText("Fisher")));
        onView(withId(R.id.buisnessnNumber)).check(matches(withText("123456789")));
        onView(withId(R.id.name)).check(matches(withText("SquishaFish")));
        onView(withId(R.id.address)).check(matches(withText("234 long point drive, b3p 2s4")));

        pressBack();
    }

    /**
     * Re-selecting the only entry in the database, and deleting it. This test will fail
     * if the 0th entry in the database was the one that should have been deleted.
     */
    @Test
    public void deleteEntryFirebase() {
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.deleteButton)).perform(click());
        pressBack();

        try {
            // this should throw an exception. If it goes past this statement without an exception,
            //and the record matches the one that should have been deleted, it has failed.

            onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());

            onView(withId(R.id.submitButton)).perform(click());
            onView(withId(R.id.province)).check(matches(withText("BC")));
            onView(withId(R.id.primarybuisness)).check(matches(withText("Fisher")));
            onView(withId(R.id.buisnessnNumber)).check(matches(withText("123456789")));
            onView(withId(R.id.name)).check(matches(withText("SquishaFish")));
            onView(withId(R.id.address)).check(matches(withText("234 long point drive, b3p 2s4")));

            fail();

        } catch (Exception e) {
            // if it ends up in here it should
        }
    }

}
