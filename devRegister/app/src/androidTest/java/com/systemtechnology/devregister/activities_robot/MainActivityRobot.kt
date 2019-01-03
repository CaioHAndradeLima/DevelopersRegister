package com.systemtechnology.devregister.activities_robot

import android.app.Activity
import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.v7.widget.RecyclerView
import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.activities.main_activity.adapter.DevelopersAdapter
import com.systemtechnology.devregister.activities.main_activity.adapter.NoneDevelopersYetAdapter
import com.systemtechnology.devregister.model.ModelDeveloper
import org.junit.Assert

class MainActivityRobot {

    fun testIsDisplayed() : MainActivityRobot {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerview)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        return this
    }

    fun testCheckAdapterIfIsCorrect(activity : Activity) : MainActivityRobot {
        val adapter = activity.findViewById<RecyclerView>( R.id.recyclerview ).adapter

        val list = ModelDeveloper().getAllDevelopers()

        if( list == null || list.isEmpty() ) {
            Assert.assertTrue(adapter is NoneDevelopersYetAdapter)
        } else {
            Assert.assertTrue( adapter is DevelopersAdapter)

            Assert.assertTrue( adapter!!.itemCount == list.size )
        }

        return this
    }
}