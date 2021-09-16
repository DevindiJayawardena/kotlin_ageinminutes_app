package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener{ view->
            clickDataPicker(view)
            //Toast.makeText(this, "Button Works!" , Toast.LENGTH_LONG).show()
        }
    }


    //this function will execute when the 'Select Date' button is clicked
    fun clickDataPicker(view : View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { //this 'OnDateSetListener' is waiting for the date to be selected by user
                    view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(
                    this,
                    "The chosen year is $selectedYear, month is ${selectedMonth+1}, day is $selectedDayOfMonth",
                    Toast.LENGTH_LONG
                ).show() //works when only a date is selected

                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                val tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)
                tvSelectedDate.setText(selectedDate)

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                val selectedDateInMinutes = theDate!!.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDate!!.time / 60000 //divide by 60000 to get in minutes. otherwise it is in ms
                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                val tvSelectedDateInMinutes = findViewById<TextView>(R.id.tvSelectedDateInMinutes)
                tvSelectedDateInMinutes.setText(differenceInMinutes.toString())

            } ,year, month, day
        )

        dpd.datePicker.setMaxDate(Date().time - 86400000) //this 86400000 is just the milliseconds of one day. So here we selected the yesterday as the latest moment
        dpd.show()
    }

}

