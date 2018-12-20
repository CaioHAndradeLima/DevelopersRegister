package com.systemtechnology.devregister.define_rules

import io.reactivex.disposables.Disposable

/**
 * the presenter that every presenter will extends
 *
 * here apply the rules for the presenter to work right
 *
 * @author Caio
 * @version 1
 * @since 1
 * @date 18/10/2018
 *
 */
abstract class RulesBasePresenter(protected val activityMethods: ActivityMethods) : PresenterAny {

    @Volatile
    private var alreadyDestroyed = false

    @Volatile
    protected var listDisposable = mutableListOf<Disposable>()


    override fun onCreate() { }

    /**
     * called when view will be destroyed
     *
     * @author Caio
     * @version 1
     * @since 1
     */
    override fun onDestroy() {
        alreadyDestroyed = true
        clearDisposables()
    }


    /**
     * verify the disposables and clear
     *
     * @author Caio
     * @since 1
     * @version 1
     */
    @Synchronized
    protected fun clearDisposables() {
        if( listDisposable != null && listDisposable.size > 0 ) {
            for (disposable in listDisposable) {
                if( !disposable.isDisposed )  disposable.dispose()
            }

            listDisposable.clear()
        }
    }


    /**
     * add disposable
     * @author Caio
     * @since 1
     * @version 1
     */
    protected fun addDisposable(dis : Disposable) {
        listDisposable.add( dis )

        if( alreadyDestroyed ) {
            clearDisposables()
        }
    }

    /**
     * remove disposable
     * @author Caio
     * @since 2
     * @version 1
     */
    protected fun removeDisposable(dis : Disposable) {
        listDisposable.remove( dis )
    }


}

/**
 * the interface with what's needed
 * for the presenter to work, in other words,
 * what the activity need to call to presenter
 * work right
 *
 * @author Caio
 * @version 1
 * @since 1
 *
 */
interface PresenterAny {

    /**
     * when activity was created
     *
     * @author Caio
     * @version 1
     * @since 1
     */
    fun onCreate()

    /**
     * when activity was destroyed
     *
     * @author Caio
     * @version 1
     * @since 1
     */
    fun onDestroy()
}