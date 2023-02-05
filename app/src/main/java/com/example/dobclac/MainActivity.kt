package com.example.dobclac

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

   private var seldate : TextView?= null
    private var tvageinmin:TextView?=null
    private var tvageinhours:TextView?=null
    private var tvageinseconds:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btndatepicker : Button=findViewById(R.id.btndatepicker)

        seldate= findViewById(R.id.tvSelectedDate)

        tvageinmin=findViewById(R.id.minutes)

        tvageinhours=findViewById(R.id.hours)
        tvageinseconds=findViewById(R.id.seconds)
        btndatepicker.setOnClickListener(){
            clickdatepicker()
        }
    }
     private fun clickdatepicker(){


        val mycal= Calendar.getInstance()
        val year=mycal.get(Calendar.YEAR)
        val month=mycal.get(Calendar.MONTH)
        val date=mycal.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
        {_,selyear, selmonth, seldayOfMnth->

            Toast.makeText(this, "Selected date $seldayOfMnth.${selmonth+1}.$selyear", Toast.LENGTH_SHORT).show()

            val selecteddate="$seldayOfMnth/${selmonth+1}/$selyear"
            seldate?.text = selecteddate

            val sdf=SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

            val thedate=sdf.parse(selecteddate)
            thedate?.let{ val selectedDateInMinutes=thedate.time/60000 //time give the time that has passes in milliseconds so dividing it by 1000 and then by 60
                            val selecteddateinhours=thedate.time/(60000*60)
                val selecteddateinseconds=thedate.time/1000
                val currentdate=sdf.parse(sdf.format(System.currentTimeMillis())).time
                val curdateinmin=currentdate/60000
                val curdateinsec=currentdate/1000
                val curdateinhour=currentdate/(60000*60)
                 currentdate?.let {
                     val timepassedinmin=curdateinmin-selectedDateInMinutes
                     tvageinmin?.text=timepassedinmin.toString()}
                val timepassedinseconds=curdateinsec-selecteddateinseconds
                tvageinseconds?.text=timepassedinseconds.toString()
                val timepassedinhours=curdateinhour-selecteddateinhours
                tvageinhours?.text=timepassedinhours.toString()

            }

        },
        year,month,date)
        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
        dpd.show()
    }
}