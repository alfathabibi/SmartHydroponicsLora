package org.d2e.smarthydroponic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cvSetTemperature.setOnClickListener {
            Intent(this, TemperatureActivity::class.java).also {
                startActivity(it)
            }
        }

        cvSetNutrition.setOnClickListener {
            Intent(this, NutritionActivity::class.java).also {
                startActivity(it)
            }
        }

        cvSetPh.setOnClickListener {
            Intent(this, PhActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}