package com.systemtechnology.devregister.activities.main_activity

import com.systemtechnology.devregister.define_rules.ActivityMethods
import com.systemtechnology.devregister.define_rules.RulesBasePresenter
import com.systemtechnology.devregister.entity.Developer
import com.systemtechnology.devregister.model.ModelDeveloper
import io.reactivex.Observable

class MainPresenter(activityMethods: MainMethods) : RulesBasePresenter(activityMethods) {
    val mainMethods = activityMethods
    private lateinit var list : MutableList<Developer>

    override fun onCreate() {
        super.onCreate()

        searchClientsAndAfterNotifyView()
    }

    private fun searchClientsAndAfterNotifyView() {
        Observable
            .fromArray(ModelDeveloper().getAllDevelopers())
            .subscribe {
                if (it != null && it.isNotEmpty()) {
                    mainMethods.whenClientsFound(it!!)
                    list = it

                } else {
                    mainMethods.whenNotExistsClientsYet()
                }
            }

    }

    fun whenClientModified(developer : Developer, inserting: Boolean) {
        if( inserting ) {

            if( ::list.isInitialized ) {
                list.add( developer )
            } else {
                searchClientsAndAfterNotifyView()
                return
            }

        } else {

            for (cl in list) {
                if( cl.id == developer.id ) {
                    list.remove( cl )
                    list.add( developer )
                    break
                }
            }
        }

        mainMethods.whenListModified( list )

    }

}

interface MainMethods : ActivityMethods {
    fun whenNotExistsClientsYet()
    fun whenClientsFound(listDevelopers : MutableList<Developer> )
    fun whenListModified(list: MutableList<Developer>)
}