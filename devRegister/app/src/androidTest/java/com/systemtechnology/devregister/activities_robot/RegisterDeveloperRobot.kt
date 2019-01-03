package com.systemtechnology.devregister.activities_robot

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.widget.EditText
import com.systemtechnology.devregister.R
import org.hamcrest.core.AllOf
import org.junit.Test

class RegisterDeveloperRobot {

    fun isDisplayed() {
        Espresso.onView(ViewMatchers.withId(R.id.edit_text_name)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.edit_text_cpf)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.textview)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.button)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.container)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.nested_scroll_layout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.button)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.text_view_photo)).check(isVisible())
        Espresso.onView(ViewMatchers.withId(R.id.card_view)).check(isVisible())
    }

    private fun isVisible() = ViewAssertions.matches(ViewMatchers.withEffectiveVisibility( ViewMatchers.Visibility.VISIBLE ))

    fun testStringsOnLayout() {
        Espresso.onView(ViewMatchers.withId(R.id.textview))
                .check(ViewAssertions.matches(ViewMatchers.withText(R.string.update_register_inserting_title)))

        Espresso.onView(ViewMatchers.withId(R.id.text_view_photo))
                .check(ViewAssertions.matches(ViewMatchers.withText(R.string.update_register_inserting_photo)))

        Espresso.onView(ViewMatchers.withId(R.id.button))
                .check(ViewAssertions.matches(ViewMatchers.withText(R.string.update_register_inserting_button)))

    }

    fun sampleClickButton_openSnackbar(){
        clickButton_openSnackbar( R.string.form_client_error_name )
    }

    private fun clickButton_openSnackbar( stringId : Int ) {
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform( ViewActions.click() )
        Espresso.onView(AllOf.allOf(ViewMatchers.withId(android.support.design.R.id.snackbar_text), ViewMatchers.withText(stringId)))
    }

    fun clickButton_openSnackbarTextCPF(){
        Espresso
                .onView( ViewMatchers.withId( R.id.edit_text_name ) )
                .perform( ViewActions.replaceText( "Caio Henrique Andrade" ) )

        clickButton_openSnackbar( R.string.form_client_error_cpf )
    }

    fun clickButton_openSnackbarTextAddress(){
        Espresso
                .onView( ViewMatchers.withId( R.id.edit_text_cpf ) )
                .perform( ViewActions.replaceText( "44574551801" ) )

        Espresso
                .onView( ViewMatchers.withId( R.id.edit_text_name ) )
                .perform( ViewActions.replaceText( "Nome Correct Example" ) )

        clickButton_openSnackbar( R.string.form_client_error_address )
    }


}
