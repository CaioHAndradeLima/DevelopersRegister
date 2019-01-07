package com.systemtechnology.devregister.activities_robot.activities_test

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import com.systemtechnology.devregister.activities.register_developer.RegisterDeveloperActivity
import com.systemtechnology.devregister.activities_robot.RegisterDeveloperRobot

@RunWith(AndroidJUnit4::class)
class RegisterDeveloperActivityTest {

    @get:Rule
    var rule = ActivityTestRule(RegisterDeveloperActivity::class.java)


    private val robot = RegisterDeveloperRobot()

    @Test
    fun isDisplayed() {
        robot.isDisplayed()
    }

    @Test
    fun testStringsOnLayout() {
        robot.testStringsOnLayout()
    }

    @Test
    fun sampleClickButton_openSnackbar() {
        robot.sampleClickButton_openSnackbar()
    }

    @Test
    fun clickButton_openSnackbarTextCPF(){
        robot.clickButton_openSnackbarTextCPF()
    }

    @Test
    fun clickButton_openSnackbarTextAddress(){
        robot.clickButton_openSnackbarTextAddress()
    }


}
