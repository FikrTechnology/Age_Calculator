package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView? = null
    private var tvAgeInMinutes : TextView? = null
    private var tvAgeInHours : TextView? = null
    private var tvAgeInDays : TextView? = null
    private var tvAgeInWeeks : TextView? = null
    private var tvAgeInMonths : TextView? = null
    private var tvAgeInYears : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPicker:Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)
        tvAgeInHours = findViewById(R.id.tvAgeInHours)
        tvAgeInDays = findViewById(R.id.tvAgeInDays)
        tvAgeInWeeks = findViewById(R.id.tvAgeInWeeks)
        tvAgeInMonths = findViewById(R.id.tvAgeInMonths)
        tvAgeInYears = findViewById(R.id.tvAgeInYears)

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val selectedDate = "$day/${month+1}/$year"

        tvSelectedDate?.text = selectedDate

        btnPicker.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{ _, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this, "Year was $selectedYear, month was ${selectedMonth+1}, day of month was $selectedDayOfMonth", Toast.LENGTH_LONG).show()

                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

                tvSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                theDate.let {
                    val selectedDateInMinutes = theDate.time / 60000
                    val selectedDateInHours = theDate.time / 3600000
                    val selectedDateInDays = theDate.time / 86400000
                    val selectedDateInWeeks = theDate.time / 604800000
                    val selectedDateInMonths = theDate.time / 2629800000
                    val selectedDateInYears = theDate.time / 31557600000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate.let {
                        // untuk perhitungan dalam konversi menit (60000 = 1 menit)
                        val currentDateInMinutes = currentDate.time / 60000
                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                        // untuk perhitungan dalam konversi jam (3600000 = 1 jam)
                        val currentDateInHours = currentDate.time / 3600000
                        val differenceInHours = currentDateInHours - selectedDateInHours

                        // untuk perhitungan dalam konversi hari (86400000 = 1 hari)
                        val currentDateInDays = currentDate.time / 86400000
                        val differenceInDays = currentDateInDays - selectedDateInDays

                        // untuk perhitungan dalam konversi minggu (604800000 = 1 minggu)
                        val currentDateInWeeks = currentDate.time / 604800000
                        val differenceInWeeks = currentDateInWeeks - selectedDateInWeeks

                        // untuk perhitungan dalam konversi bulan (2629800000 = 1 bulan)
                        val currentDateInMonths = currentDate.time / 2629800000
                        val differenceInMonths = currentDateInMonths - selectedDateInMonths

                        // untuk perhitungan dalam konversi tahun (31557600000 = 1 tahun)
                        val currentDateInYears = currentDate.time / 31557600000
                        val differenceInYears = currentDateInYears - selectedDateInYears

                        tvAgeInMinutes?.text = differenceInMinutes.toString()
                        tvAgeInHours?.text = differenceInHours.toString()
                        tvAgeInDays?.text = differenceInDays.toString()
                        tvAgeInWeeks?.text = differenceInWeeks.toString()
                        tvAgeInMonths?.text = differenceInMonths.toString()
                        tvAgeInYears?.text = differenceInYears.toString()

                    }
                }
            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}