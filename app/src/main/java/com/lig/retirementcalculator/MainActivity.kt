package com.lig.retirementcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCenter.setLogLevel(Log.VERBOSE)
        AppCenter.start(application, "15666653-f39c-403b-a5b3-5539438a853e", Analytics::class.java, Crashes::class.java)
        val btn  = findViewById<Button>(R.id.calculateButton)
        btn.setOnClickListener {
            //Crashes.generateTestCrash()
            //throw Exception("Something wrong happened")
        }
    }
}