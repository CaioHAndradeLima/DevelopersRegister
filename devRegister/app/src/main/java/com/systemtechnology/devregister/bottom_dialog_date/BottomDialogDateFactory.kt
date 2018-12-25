package com.systemtechnology.devregister.bottom_dialog_date

import android.app.Activity
import android.support.v4.content.ContextCompat
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog
import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.utils_date.UtilsDate
import java.util.*

object BottomDialogDateFactory {

    private const val DATA_MAX_AFTER_CURRENT_DATA = 15
    private const val MINUTES_BETWEEN_HOURS = 15


    fun build( activity : Activity , listener : (date : Date) -> Unit) : SingleDateAndTimePickerDialog {

        return  SingleDateAndTimePickerDialog
                    .Builder(activity)
                    .bottomSheet()
                    .curved()
                    .title("Data de entrega")
                    .titleTextColor(getColorCompat( R.color.blue_strong , activity ))
                    .backgroundColor(getColorCompat( android.R.color.white , activity ))
                    .mainColor( getColorCompat( R.color.blue_strong , activity ))
                    .mustBeOnFuture()
                    //  .displayListener({ Toast.makeText( this , "${it.date}" , Toast.LENGTH_SHORT).show() })
                    .listener( listener )
                    .minDateRange( UtilsDate.getToday() )
                    .maxDateRange( UtilsDate.getDateAfterDaysPassedByParam( DATA_MAX_AFTER_CURRENT_DATA  ) )
                    //        .displayDays(true)
                    .displayMonth(true)
                    .displayYears(false )
                    .displayHours(true)
                    .displayMinutes(true)
                    .displayAmPm( false )
                    //.displayMonthNumbers( false )
                    .minutesStep(MINUTES_BETWEEN_HOURS)
                    .build()
    }

    private fun getColorCompat( color : Int , activity : Activity ) : Int {
        return ContextCompat.getColor( activity , color )
    }
}
