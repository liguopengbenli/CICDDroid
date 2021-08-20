package com.lig.retirementcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCenter.setLogLevel(Log.VERBOSE)
        AppCenter.start(application, "15666653-f39c-403b-a5b3-5539438a853e", Analytics::class.java, Crashes::class.java)
        val btn  = findViewById<Button>(R.id.calculateButton)
        val editText_interest = findViewById<EditText>(R.id.interestEditText)
        val editText_current_age = findViewById<EditText>(R.id.ageEditText)
        val editText_retirement_age = findViewById<EditText>(R.id.retirementEditText)

        btn.setOnClickListener {
            //Crashes.generateTestCrash()
            //throw Exception("Something wrong happened")
            try {
                val interstRate = editText_interest.text.toString().toFloat()
                val currentAge = editText_current_age.text.toString().toInt()
                val retirementAge = editText_retirement_age.text.toString().toInt()
                val properties: HashMap<String, String> = HashMap()
                properties.put("interest_rate", interstRate.toString())
                properties.put("current_age", currentAge.toString())
                properties.put("retirement_age", retirementAge.toString())

                if(interstRate <= 0){
                    Analytics.trackEvent("Wrong_interest_rate", properties)
                }
                if(retirementAge <= currentAge){
                    Analytics.trackEvent("Wrong age", properties)
                }
            }catch (ex: Exception){
                Analytics.trackEvent(ex.message)
            }


        }
    }
}