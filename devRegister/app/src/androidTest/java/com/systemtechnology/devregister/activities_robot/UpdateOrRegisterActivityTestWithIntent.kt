package com.systemtechnology.devregister.activities_robot

import android.content.Intent

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.google.gson.Gson

import com.systemtechnology.devregister.activities.register_developer.RegisterDeveloperActivity
import com.systemtechnology.devregister.entity.AddressEntity
import com.systemtechnology.devregister.entity.DeveloperEntity

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UpdateOrRegisterActivityTestWithIntent {


    @get :Rule
    var rule: ActivityTestRule<RegisterDeveloperActivity> =
        object : ActivityTestRule<RegisterDeveloperActivity>(RegisterDeveloperActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val intent = Intent()

                val developer = DeveloperEntity()

                developer.name = "Caio Henrique"
                developer.CPF  = "44574551801"

                developer.addressEntity = AddressEntity()
                developer.addressEntity.houseNumber = "29"
                developer.addressEntity.street      = "rua imaculada"
                developer.addressEntity.state       = "sp"
                developer.addressEntity.city        = "guarulhos"
                developer.addressEntity.CEP         = "07183070"
                developer.addressEntity.neighborhood= "São Manoel"


                intent.putExtra(RegisterDeveloperActivity.EXTRA_DEVELOPER , Gson().toJson( developer ) )
                return intent
            }
        }

    @Test
    fun any() {}

}