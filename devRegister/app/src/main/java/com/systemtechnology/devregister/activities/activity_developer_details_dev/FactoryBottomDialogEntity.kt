package com.systemtechnology.devregister.activities.activity_developer_details_dev

import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.bottom_dialogs.OptionsBottomSheetEntity
import com.systemtechnology.devregister.entity.ActivityDevEntity

object FactoryBottomDialogEntity {

    fun createOptions( ade : ActivityDevEntity ,
                       whenOptionClicked : (newStatus : ActivityDevEntity.ActivityStatus ) -> Unit )

                        : MutableList<OptionsBottomSheetEntity> {


        return if( ade.isDelivered() ) {
            arrayListOf( getHistoricActivityDev(whenOptionClicked) )
        } else if( ade.isExecuting() ) {
            arrayListOf(
                    getOptionUpdateToStopped(whenOptionClicked) ,
                    getOptionUpdateToDelivered( whenOptionClicked ) ,
                    getOptionDelete(whenOptionClicked)
                        )
        } else if( ade.isStopped() ) {
            arrayListOf(
                    getOptionUpdateToExecuting(whenOptionClicked) ,
                    getOptionDelete(whenOptionClicked)

            )
        } else throw IllegalStateException("not implemented yet ${ade.status}")

    }


    private fun getOptionUpdateToExecuting(whenOptionClicked : (newStatus : ActivityDevEntity.ActivityStatus ) -> Unit)

                                            : OptionsBottomSheetEntity {


        return OptionsBottomSheetEntity(
                R.drawable.ic_activity_executing,
                    "Alterar para executando" ) {

                whenOptionClicked( ActivityDevEntity.ActivityStatus.EXECUTING )
            }
    }


    private fun getOptionUpdateToDelivered(whenOptionClicked : (newStatus : ActivityDevEntity.ActivityStatus ) -> Unit)

            : OptionsBottomSheetEntity {


        return OptionsBottomSheetEntity(
                R.drawable.ic_activity_done,
                "Alterar para entregue" ) {

            whenOptionClicked( ActivityDevEntity.ActivityStatus.DELIVERED )
        }
    }


    private fun getOptionUpdateToStopped(whenOptionClicked : (newStatus : ActivityDevEntity.ActivityStatus ) -> Unit)

            : OptionsBottomSheetEntity {


        return OptionsBottomSheetEntity(
                R.drawable.ic_activity_stopped,
                "Parar atividade" ) {

            whenOptionClicked( ActivityDevEntity.ActivityStatus.STOPPED )
        }
    }

    private fun getOptionDelete(whenOptionClicked : (newStatus : ActivityDevEntity.ActivityStatus ) -> Unit)

            : OptionsBottomSheetEntity {


        return OptionsBottomSheetEntity(
                android.R.drawable.ic_delete,
                "Deletar atividade" ) {

            whenOptionClicked( ActivityDevEntity.ActivityStatus.DELETED )
        }
    }


    private fun getHistoricActivityDev(whenOptionClicked : (newStatus : ActivityDevEntity.ActivityStatus ) -> Unit)

            : OptionsBottomSheetEntity {


        return OptionsBottomSheetEntity(
                android.R.drawable.ic_delete,
                "Histórico da atividade" ) {

            whenOptionClicked( ActivityDevEntity.ActivityStatus.DELIVERED )


        }
    }
}
