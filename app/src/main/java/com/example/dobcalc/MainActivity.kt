package com.example.dobcalc

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datePickerBtn : Button = findViewById(R.id.buttonDatePicker)
       var mySelectedDate : TextView = findViewById(R.id.date)
        var ageInMinutes : TextView = findViewById(R.id.minutes)



        fun clickDatePicker() {
            val myCalendar = Calendar.getInstance()
            val year = myCalendar.get(Calendar.YEAR)
            val month = myCalendar.get(Calendar.MONTH)
            val day = myCalendar.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{view , selectedYear, selectedMonth, selectedDayOfMonth ->

             Toast.makeText(this, "Year was $selectedYear ," +
                     " Month was ${selectedMonth+1}, Date was $selectedDayOfMonth", Toast.LENGTH_LONG).show()

                val selectedDate = "$selectedDayOfMonth/${selectedMonth +1}/$selectedYear"

                mySelectedDate.text = selectedDate

                val sdf = java.text.SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                //using type safety so the code don't crash when date is empty
                theDate?.let {
                    val mySelectedDateInMinutes = theDate.time / 60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val myCurrentDateInMinutes = currentDate.time/ 60000
                        val diff = myCurrentDateInMinutes - mySelectedDateInMinutes

                        ageInMinutes.text = diff.toString()
                    }
                    }



            },
                year,
                month,
                day)

            dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
            dpd.show()


        }


        datePickerBtn.setOnClickListener{
            clickDatePicker()
        }






        }




}