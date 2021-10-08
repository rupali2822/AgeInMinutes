package com.infeanet.ageinminutes

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*




class MainActivity : AppCompatActivity() {
    var age_hr: Int = 0
    var age_min: Int = 0
    var year: Int = 0
    var month: Int = 0
    var day:Int=0
    var year_old: Long = 0
    var yr:Long = 0
    var months:Long=0
    var days:Long=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_DatePicker.setOnClickListener { view ->
            clickDatePicker(view)

        }
        btn_calculate.setOnClickListener {



            text_total_age.visibility = View.VISIBLE
            text_total_age.text =
                "Years= ${yr},Months= ${months} ,days= ${days},hours= ${age_hr},minutes=${age_min}"

            Toast.makeText(this, "Now your age is:${year_old}", Toast.LENGTH_SHORT).show()


        }
        btn_TimePicker.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                var selected_time = SimpleDateFormat("HH:mm").format(cal.time)


                val myCalendar = Calendar.getInstance()
                val current_hr = myCalendar.get(Calendar.HOUR_OF_DAY)
                val current_min = myCalendar.get(Calendar.MINUTE)

                 age_hr = current_hr - hour
                 age_min = current_min - minute


                tvSelectedTime.setText(selected_time)

            }
            TimePickerDialog(
                this,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()
        }


    }

    @SuppressLint("SetTextI18n")
    fun clickDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
         year = myCalendar.get(Calendar.YEAR)
        month = myCalendar.get(Calendar.MONTH)
         day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->


                // Toast.makeText(this, "The chosen year is $selectedYear,the month is ${selectedMonth + 1},and day is $selectedDayOfMonth", Toast.LENGTH_SHORT).show()

                // TimePickerDialog(this,DatePickerDialog.OnTimeSetListner())

                var selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                tvSelectedDate.setText(selectedDate)


                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)


                val selectedDateInMinutes = theDate!!.time / 60000

                //get year
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))


                val currentDateToMinutes = currentDate!!.time / 60000

                val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes


            year_old = differenceInMinutes / 525600


                var timeSec = differenceInMinutes % 525600 * 60
                 yr = year_old

                months = (month-selectedMonth).toLong()

                Log.d(
                    "months","$months  $yr"
                )

               days=day-selectedDayOfMonth.toLong()




            },


            year,
            month,
            day
        )
        dpd.datePicker.maxDate = myCalendar.timeInMillis
        dpd.show()
    }



}



