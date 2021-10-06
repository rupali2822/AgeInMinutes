package com.infeanet.ageinminutes

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_DatePicker.setOnClickListener { view ->
            clickDatePicker(view)

        }

    }
    @SuppressLint("SetTextI18n")
    fun clickDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->


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

            tvSelectedDateInMinutes.setText(differenceInMinutes.toString())


            val year_old = differenceInMinutes / 525600
            tvAge.setText(year_old.toString())


            var timeSec = differenceInMinutes%525600*60
            val yr=year_old
            var months = yr/ 12
            var days = timeSec/86400
            var hr = timeSec%3600000/600000




            txt_yourAge.visibility = View.VISIBLE
            txt_yourAge.setText("Months= ${months} ,days= ${days},hours= ${hr},seconds=${timeSec}")





            Toast.makeText(this, "Now your age is:${year_old}", Toast.LENGTH_SHORT).show()
        },


                year, month, day)
        dpd.datePicker.maxDate = myCalendar.timeInMillis
        dpd.show()
    }

//current time
    fun calculateAge(){
        val now = Calendar.getInstance()
        val c_year = now[Calendar.YEAR]
        val c_month = now[Calendar.MONTH] + 1 // Note: zero based!

        val c_day = now[Calendar.DAY_OF_MONTH]
        val c_hour = now[Calendar.HOUR_OF_DAY]
        val c_minute = now[Calendar.MINUTE]
        val c_second = now[Calendar.SECOND]
        val c_millis = now[Calendar.MILLISECOND]

        System.out.printf("%d-%02d-%02d %02d:%02d:%02d.%03d", c_year, c_month, c_day, c_hour, c_minute, c_second, c_millis)



    }


    }



