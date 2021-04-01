package org.d2e.smarthydroponic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import org.d2e.smarthydroponic.auth.RegisterActivity
import org.d2e.smarthydroponic.process.NutritionActivity
import org.d2e.smarthydroponic.process.PhActivity
import org.d2e.smarthydroponic.process.PumpActivity
import org.d2e.smarthydroponic.process.TemperatureActivity

class MainActivity : AppCompatActivity() {


    private val viewModel : MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.data.observe(this, Observer {

            Ph.text = it.status.phValue.toString()
            temperature.text = it.status.tempratureValue.toString()
            Nutri.text = it.status.tdsValue.toString()

            if(it.command.flowPump == 0){
                statusWaterPump.text = getString(R.string.off)
            }else{
                statusWaterPump.text = getString(R.string.on)
            }

            if(it.status.mixPump == 0){
                statusMixPump.text = getString(R.string.off)
            }else{
                statusMixPump.text = getString(R.string.on)
            }

            if(it.status.cooler == 0){
                statusCoolingFan.text = getString(R.string.off)
            }else{
                statusCoolingFan.text = getString(R.string.on)
            }

            if(it.status.heater == 0){
                statusWaterHeater.text = getString(R.string.off)
            }else{
                statusWaterHeater.text = getString(R.string.on)
            }

            if (it.status.nutriup == 0 && it.status.nutridown == 1){
                statusNutriUp.text = getString(R.string.down)
            }else if(it.status.nutriup == 1 && it.status.nutridown == 0){
                statusNutriUp.text = getString(R.string.up)
            }else{
                statusNutriUp.text = getString(R.string.off)
            }

            if (it.status.phup == 0 && it.status.phdown == 1){
                statusPhUp.text = getString(R.string.down)
            }else if(it.status.phup == 1 && it.status.phdown == 0){
                statusPhUp.text = getString(R.string.up)
            }else{
                statusPhUp.text = getString(R.string.off)
            }
        })

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

        cvSetPump.setOnClickListener {
            Intent(this, PumpActivity::class.java).also {
                startActivity(it)
            }
        }
        ivSetting.setOnClickListener {
            Intent(this, RegisterActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}