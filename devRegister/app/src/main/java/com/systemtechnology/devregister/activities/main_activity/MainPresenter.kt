package com.systemtechnology.devregister.activities.main_activity

import com.systemtechnology.devregister.define_rules.ActivityMethods
import com.systemtechnology.devregister.define_rules.RulesBasePresenter
import com.systemtechnology.devregister.entity.DeveloperEntity
import com.systemtechnology.devregister.model.ModelDeveloper
import io.reactivex.Observable

class MainPresenter(activityMethods: MainMethods) : RulesBasePresenter(activityMethods) {

    val mainMethods = activityMethods
    private lateinit var list : MutableList<DeveloperEntity>

    override fun onCreate() {
        super.onCreate()

        searchClientsAndAfterNotifyView()
    }

    private fun searchClientsAndAfterNotifyView() {
        Observable
            .fromArray(ModelDeveloper().getAllDevelopers())
            .subscribe {
                if (it != null && it.isNotEmpty()) {
                    mainMethods.whenDeveloperFound(it!!)
                    list = it

                } else {
                    mainMethods.whenNotExistsDevelopersYet()
                }
            }

    }

    fun whenClientModified(developerEntity : DeveloperEntity, inserting: Boolean) {
        if( inserting ) {

            if( ::list.isInitialized ) {
                list.add( developerEntity )
            } else {
                searchClientsAndAfterNotifyView()
                return
            }

        } else {

            for (cl in list) {
                if( cl.id == developerEntity.id ) {
                    list.remove( cl )
                    list.add( developerEntity )
                    break
                }
            }
        }

        mainMethods.whenListModified( list )

    }

}

interface MainMethods : ActivityMethods {
    fun whenNotExistsDevelopersYet()
    fun whenDeveloperFound(listDeveloperEntities : MutableList<DeveloperEntity> )
    fun whenListModified(list: MutableList<DeveloperEntity>)
}