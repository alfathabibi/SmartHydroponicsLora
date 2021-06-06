package org.d2e.smarthydroponic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import org.d2e.smarthydroponic.auth.LoginActivity
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

        viewModel.authState.observe(this, Observer {
            if(it == null){
                Intent(this, LoginActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        })

        viewModel.data.observe(this, Observer {
            progressBarMain.visibility = View.VISIBLE
            if (it != null) {
                progressBarMain.visibility = View.GONE
                Ph.text = it.status.phValue.toString()
                temperature.text = it.status.tempratureValue.toString()
                Nutri.text = it.status.tdsValue.toString()

                if (it.command.flowPump == 0) {
                    statusWaterPump.text = getString(R.string.off)
                    statusWaterPump.setTextColor(resources.getColor(R.color.merah))
                } else {
                    statusWaterPump.text = getString(R.string.on)
                    statusWaterPump.setTextColor(resources.getColor(R.color.tosca))
                }

                if (it.status.mixPump == 0) {
                    statusMixPump.text = getString(R.string.off)
                    statusMixPump.setTextColor(resources.getColor(R.color.merah))
                } else {
                    statusMixPump.text = getString(R.string.on)
                    statusMixPump.setTextColor(resources.getColor(R.color.tosca))
                }

                if (it.status.cooler == 0) {
                    statusCoolingFan.text = getString(R.string.off)
                    statusCoolingFan.setTextColor(resources.getColor(R.color.merah))
                } else {
                    statusCoolingFan.text = getString(R.string.on)
                    statusCoolingFan.setTextColor(resources.getColor(R.color.tosca))
                }

                if (it.status.heater == 0) {
                    statusWaterHeater.text = getString(R.string.off)
                    statusWaterHeater.setTextColor(resources.getColor(R.color.merah))
                } else {
                    statusWaterHeater.text = getString(R.string.on)
                    statusWaterHeater.setTextColor(resources.getColor(R.color.tosca))
                }

                if (it.status.nutriup == 0 && it.status.nutridown == 1) {
                    statusNutriUp.text = getString(R.string.down)
                    statusNutriUp.setTextColor(resources.getColor(R.color.bitos))
                } else if (it.status.nutriup == 1 && it.status.nutridown == 0) {
                    statusNutriUp.text = getString(R.string.up)
                    statusNutriUp.setTextColor(resources.getColor(R.color.tosca))
                } else {
                    statusNutriUp.text = getString(R.string.off)
                    statusNutriUp.setTextColor(resources.getColor(R.color.merah))
                }

                if (it.status.phup == 0 && it.status.phdown == 1) {
                    statusPhUp.text = getString(R.string.down)
                    statusPhUp.setTextColor(resources.getColor(R.color.bitos))
                } else if (it.status.phup == 1 && it.status.phdown == 0) {
                    statusPhUp.text = getString(R.string.up)
                    statusPhUp.setTextColor(resources.getColor(R.color.tosca))
                } else {
                    statusPhUp.text = getString(R.string.off)
                    statusPhUp.setTextColor(resources.getColor(R.color.merah))
                }
            }else{
                progressBarMain.visibility = View.VISIBLE
                Intent(this, SettingActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
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
            Intent(this, SettingActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}