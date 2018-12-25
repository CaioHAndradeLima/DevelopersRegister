package com.systemtechnology.devregister.configs

object ConfigActionsBroadManager {

    /**
     * send by ActivityRegister
     *
     * sent when was inserted new AtivityDevEntity or updated
     */
    const val ACTION_ACTIVITY_DEV = "ACTION_ACT_DEV"

    /**
     *
     * send by ActivityRegister
     *
     * when the address was completed
     */
    const val ACTION_ADDRESS_COMPLETED = "ACTION_ADDRESS_COMPLETED"


    /**
     * send by RegisterDeveloperActivity
     *
     * when the developer was registered
     */
    const val ACTION_DEVELOPER_REGISTER = "ACTION_DEVELOPER_REGISTER"


}
