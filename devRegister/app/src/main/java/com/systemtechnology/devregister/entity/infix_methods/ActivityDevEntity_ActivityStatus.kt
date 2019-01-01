package com.systemtechnology.devregister.entity.infix_methods

import com.systemtechnology.devregister.entity.ActivityDevEntity

fun ActivityDevEntity.isStopped() : Boolean {
    return status == ActivityDevEntity.ActivityStatus.STOPPED
}

fun ActivityDevEntity.isExecuting(): Boolean {
    return status == ActivityDevEntity.ActivityStatus.EXECUTING
}

fun ActivityDevEntity.isDelivered(): Boolean {
    return status == ActivityDevEntity.ActivityStatus.DELIVERED
}

fun ActivityDevEntity.isDeleted(): Boolean {
    return status == ActivityDevEntity.ActivityStatus.DELETED
}