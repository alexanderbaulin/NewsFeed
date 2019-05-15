package com.baulin.alexander.newsfeed;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.widget.SwipeRefreshLayout;

import com.baulin.alexander.newsfeed.mvp.view.activities.Main;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public final ActivityRule<Main> main = new ActivityRule<>(Main.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.baulin.alexander.newsfeed", appContext.getPackageName());
    }


    @Test
    public void portraitActivityTest() {
        SwipeRefreshLayout refreshLayout = main.get().findViewById(R.id.swiperefresh);

        while (true) {
            if(!refreshLayout.isRefreshing()) break;
        }
        onView(withId(R.id.recView)).perform(swipeDown());

        main.get().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        onView(ViewMatchers.withId(R.id.recView)).perform(RecyclerViewActions.actionOnItemAtPosition(main.get().getRecyclerViewItemsCount()-1, click()));
    }

    @Test
    public void landscapeActivityTest() {
        SwipeRefreshLayout refreshLayout = main.get().findViewById(R.id.swiperefresh);

        while (true) {
            if(!refreshLayout.isRefreshing()) break;
        }
        onView(withId(R.id.recView)).perform(swipeDown());

        main.get().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(ViewMatchers.withId(R.id.recView)).perform(RecyclerViewActions.actionOnItemAtPosition(main.get().getRecyclerViewItemsCount()-1, click()));
    }
}
