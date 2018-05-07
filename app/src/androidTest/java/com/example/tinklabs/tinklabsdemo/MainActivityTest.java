package com.example.tinklabs.tinklabsdemo;

import android.support.test.espresso.Espresso;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertNotNull;

/**
 * Created by ryze.liu on 5/5/2018.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest{


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void tabDisplayed_test(){
        Espresso.onView(ViewMatchers.withId(R.id.tabs)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
    @Test
    public void recycleViewDisplayed_test(){
        ViewInteraction viewPage = Espresso.onView(ViewMatchers.withId(R.id.viewpager)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        assertNotNull(viewPage);
        viewPage.perform(ViewActions.swipeLeft());
        Espresso.onView(Matchers.allOf(ViewMatchers.withText(R.string.tab_shop),ViewMatchers.isSelected()));
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.recycler_view),ViewMatchers.isDisplayed()));
        viewPage.perform(ViewActions.swipeLeft());
        Espresso.onView(Matchers.allOf(ViewMatchers.withText(R.string.tab_eat),ViewMatchers.isSelected()));
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.recycler_view),ViewMatchers.isDisplayed()));
    }
    @Test
    public void cityTextDisplayed_test(){
        Espresso.onView(ViewMatchers.withText(R.string.tab_guide)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withText(R.string.tab_guide)).check(ViewAssertions.matches(ViewMatchers.isEnabled()));
        Espresso.onView(ViewMatchers.withText(R.string.tab_guide)).perform(ViewActions.click()).check(ViewAssertions.matches(ViewMatchers.isSelected()));
    }

    @Test
    public void shopTextDisplayed_test(){
        Espresso.onView(ViewMatchers.withText(R.string.tab_shop)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withText(R.string.tab_shop)).check(ViewAssertions.matches(ViewMatchers.isEnabled()));
        Espresso.onView(ViewMatchers.withText(R.string.tab_shop)).perform(ViewActions.click()).check(ViewAssertions.matches(ViewMatchers.isSelected()));
    }

    @Test
    public void eatTextDisplayed_test(){
        Espresso.onView(ViewMatchers.withText(R.string.tab_eat)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withText(R.string.tab_eat)).check(ViewAssertions.matches(ViewMatchers.isEnabled()));
        Espresso.onView(ViewMatchers.withText(R.string.tab_eat)).perform(ViewActions.click()).check(ViewAssertions.matches(ViewMatchers.isSelected()));

    }
    @Test
    public void recycleView_is_exist(){
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.recycler_view),ViewMatchers.isDisplayed()));
    }

}