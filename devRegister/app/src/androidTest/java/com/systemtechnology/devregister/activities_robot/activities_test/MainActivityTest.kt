package com.systemtechnology.devregister.activities_robot.activities_test

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import com.systemtechnology.devregister.activities.main_activity.MainActivity
import com.systemtechnology.devregister.activities_robot.MainActivityRobot

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest  {

    @get :Rule
    var rule : ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java)

    val robot = MainActivityRobot()

    @Test
    fun checkViews_isDisplayed() {
        robot.testIsDisplayed()
    }

    @Test
    fun checkAdapterIfIsCorrect() {
        robot.testCheckAdapterIfIsCorrect( rule.activity )
    }

}
