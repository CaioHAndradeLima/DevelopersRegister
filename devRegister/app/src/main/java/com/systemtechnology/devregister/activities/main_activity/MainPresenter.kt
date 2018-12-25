package com.systemtechnology.devregister.activities.main_activity

import com.systemtechnology.devregister.define_rules.ActivityMethods
import com.systemtechnology.devregister.define_rules.RulesBasePresenter
import com.systemtechnology.devregister.entity.ActivityDevEntity
import com.systemtechnology.devregister.entity.DeveloperEntity
import com.systemtechnology.devregister.model.ModelDeveloper
import io.reactivex.Observable
import java.lang.IllegalStateException

class MainPresenter(val mainMethods : MainMethods) : RulesBasePresenter(mainMethods) {

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

    fun whenDeveloperModified(developerEntity : DeveloperEntity, inserting: Boolean) {
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

    fun whenActivityDevEntityModified(ade: ActivityDevEntity, isInserting: Boolean) {
        if( isInserting )
            for ( (index , it ) in list.withIndex() ) {
                if( it.thisActivityBelongsThisDev( ade ) ) {
                    it.addActivityDev( ade )
                    mainMethods.whenNewActivityDevInserted( ade , index )
                    break
                }
            }
        else
            throw IllegalStateException("not implemented yet")
    }

}

interface MainMethods : ActivityMethods {
    fun whenNotExistsDevelopersYet()
    fun whenDeveloperFound(listDeveloperEntities : MutableList<DeveloperEntity> )
    fun whenListModified(list: MutableList<DeveloperEntity>)
    fun whenNewActivityDevInserted(ade: ActivityDevEntity, indexPosition : Int)
}