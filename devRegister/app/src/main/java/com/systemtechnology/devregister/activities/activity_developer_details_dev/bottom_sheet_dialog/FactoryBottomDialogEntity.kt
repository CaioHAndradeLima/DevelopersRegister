package com.systemtechnology.devregister.activities.activity_developer_details_dev.bottom_sheet_dialog

import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.bottom_dialogs.OptionsBottomSheetEntity
import com.systemtechnology.devregister.entity.ActivityDevEntity
import com.systemtechnology.devregister.entity.infix_methods.*

object FactoryBottomDialogEntity {

    fun createOptions( ade : ActivityDevEntity )

                        : MutableList<OptionsBottomSheetEntity> {

        return when {
            ade.isDelivered() -> arrayListOf(getHistoricActivityDev())

            ade.isExecuting() -> arrayListOf(
                getOptionUpdateToStopped(),
                getOptionUpdateToDelivered(),
                getOptionDelete()
            )

            ade.isStopped() -> arrayListOf(
                getOptionUpdateToExecuting(),
                getOptionDelete()
            )

            else -> throw IllegalStateException("not implemented yet ${ade.status}")
        }

    }


    private fun getOptionUpdateToExecuting()

                                            : OptionsBottomSheetEntity {

        return OptionsBottomSheetEntity(
                R.drawable.ic_activity_executing,
                    "Alterar para executando",
                ActivityDevEntity.ActivityStatus.EXECUTING.name)
    }


    private fun getOptionUpdateToDelivered()

            : OptionsBottomSheetEntity {

        return OptionsBottomSheetEntity(
                R.drawable.ic_activity_done,
                "Alterar para entregue" ,
                ActivityDevEntity.ActivityStatus.DELIVERED.name )
    }


    private fun getOptionUpdateToStopped()

            : OptionsBottomSheetEntity {


        return OptionsBottomSheetEntity(
                R.drawable.ic_activity_stopped,
                "Parar atividade",
                ActivityDevEntity.ActivityStatus.STOPPED.name
                )
    }

    private fun getOptionDelete()

            : OptionsBottomSheetEntity {


        return OptionsBottomSheetEntity(
                android.R.drawable.ic_delete,
                "Deletar atividade" ,
                ActivityDevEntity.ActivityStatus.DELETED.name
                )
    }


    private fun getHistoricActivityDev()

            : OptionsBottomSheetEntity {


        return OptionsBottomSheetEntity(
                android.R.drawable.ic_delete,
                "Histórico da atividade" ,
                ActivityDevEntity.ActivityStatus.DELIVERED.name
                )
    }

    fun getStatusByOption(orbd: OptionsBottomSheetEntity) : ActivityDevEntity.ActivityStatus {
        return ActivityDevEntity.ActivityStatus.valueOf( orbd.id )
    }
}
