package com.systemtechnology.devregister.activities.main_activity

import com.systemtechnology.devregister.define_rules.ActivityMethods
import com.systemtechnology.devregister.define_rules.RulesBasePresenter
import com.systemtechnology.devregister.entity.ActivityDevEntity
import com.systemtechnology.devregister.entity.DeveloperEntity
import com.systemtechnology.devregister.model.ModelDeveloper
import io.reactivex.Observable

class MainPresenter(val mainMethods : MainMethods) : RulesBasePresenter(mainMethods) {

    lateinit var list : MutableList<DeveloperEntity>
    private set

    override fun onCreate() {
        super.onCreate()

        searchClientsAndAfterNotifyView()
    }

    private fun searchClientsAndAfterNotifyView() {
        Observable
            .fromArray(ModelDeveloper().getAllDevelopers())
            .doOnNext {
                if (it != null && it.isNotEmpty()) {
                    mainMethods.whenDeveloperFound(it!!)
                    list = it

                } else {
                    mainMethods.whenNotExistsDevelopersYet()
                }
            }.subscribe()

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

    fun whenActivityDevEntityWasInserted(ade: ActivityDevEntity) {
        for ((index, it) in list.withIndex()) {
            if (it.thisActivityBelongsThisDev(ade)) {
                it.addActivityDev(ade)
                mainMethods.whenNeedUpdateHolderActivityDev(ade, index)
                break
            }
        }
    }

    fun getDeveloperByCPF(CPF : String) : DeveloperEntity? {
        for (developerEntity in list) {
            if( developerEntity.CPF == CPF ) {
                return developerEntity
            }
        }

        return null
    }

}

interface MainMethods : ActivityMethods {
    fun whenNotExistsDevelopersYet()
    fun whenDeveloperFound(listDeveloperEntities : MutableList<DeveloperEntity> )
    fun whenListModified(list: MutableList<DeveloperEntity>)
    fun whenNeedUpdateHolderActivityDev(ade: ActivityDevEntity, indexPosition : Int)
}