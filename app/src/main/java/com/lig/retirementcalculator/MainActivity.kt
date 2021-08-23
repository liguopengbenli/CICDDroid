package com.lig.retirementcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCenter.setLogLevel(Log.VERBOSE)
        AppCenter.start(
            application,
            "01d935a9-cb36-4953-946f-7032bfe56b8c",
            Analytics::class.java,
            Crashes::class.java
        )
        val future = Crashes.hasCrashedInLastSession()

        future.thenAccept {
            if (it){
                Toast.makeText(this, "Oops! sorry about that", Toast.LENGTH_LONG).show()
            }
        }


        val btn = findViewById<Button>(R.id.calculateButton)
        val editText_interest = findViewById<EditText>(R.id.interestEditText)
        val editText_current_age = findViewById<EditText>(R.id.ageEditText)
        val editText_retirement_age = findViewById<EditText>(R.id.retirementEditText)
        val editText_monthly_saving = findViewById<EditText>(R.id.monthlySavingsEditText)

        val textview_result = findViewById<TextView>(R.id.resultTextView)

        btn.setOnClickListener {
            Crashes.generateTestCrash()
            //throw Exception("Something wrong happened")
            try {
                val interstRate = editText_interest.text.toString().toFloat()
                val currentAge = editText_current_age.text.toString().toInt()
                val retirementAge = editText_retirement_age.text.toString().toInt()
                val monthlySaving = editText_monthly_saving.text.toString().toInt()
                val properties: HashMap<String, String> = HashMap()
                properties.put("interest_rate", interstRate.toString())
                properties.put("current_age", currentAge.toString())
                properties.put("retirement_age", retirementAge.toString())
                properties.put("saving_month", monthlySaving.toString())


                if (interstRate <= 0) {
                    Analytics.trackEvent("Wrong_interest_rate", properties)
                }
                if (retirementAge <= currentAge) {
                    Analytics.trackEvent("Wrong age you can't retire before", properties)
                }
                //fake future saving
                val futureSaving = monthlySaving * 12 * interstRate
                textview_result.text =
                    "At current rate of $interstRate, we have future saving $futureSaving yearly"


            } catch (ex: Exception) {
                Analytics.trackEvent(ex.message)
            }
        }
    }
}
