package org.d2e.smarthydroponic.process

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_temperature.*
import org.d2e.smarthydroponic.R

class TemperatureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperature)

        ivBackTemperature.setOnClickListener {
            finish()
        }

        val viewModel = ViewModelProvider(this).get(TemperatureViewModel::class.java)
        viewModel.data.observe(this, Observer {
            tvCurrentTemperature.text = getString(R.string.current_temperature_set, it.command.minTemp, it.command.maxTemp)
        })

        cvBtnSetMinT.setOnClickListener {
            val minTempSet = etMinTemperature.text.toString()
            if (minTempSet.isEmpty()){
                etMinTemperature.error = getString(R.string.please_fill_the_blank)
                etMinTemperature.requestFocus()
                return@setOnClickListener
            }
            if (minTempSet.toDouble() < 0){
                etMinTemperature.error = getString(R.string.min_temperature_error)
                etMinTemperature.requestFocus()
                return@setOnClickListener
            }

            viewModel.updateMinTemperature(minTempSet.toDouble())
            Toast.makeText(this, getString(R.string.update_successfull), Toast.LENGTH_SHORT).show()
            etMinTemperature.text.clear()
        }

        cvBtnSetMaxT.setOnClickListener {
            val maxTempSet = etMaxTemperature.text.toString()
            if (maxTempSet.isEmpty()){
                etMinTemperature.error = getString(R.string.please_fill_the_blank)
                etMinTemperature.requestFocus()
                return@setOnClickListener
            }
            if (maxTempSet.toDouble() < 0){
                etMinTemperature.error = getString(R.string.min_temperature_error)
                etMinTemperature.requestFocus()
                return@setOnClickListener
            }

            viewModel.updateMaxTemperature(maxTempSet.toDouble())
            Toast.makeText(this, getString(R.string.update_successfull), Toast.LENGTH_SHORT).show()
            etMaxTemperature.text.clear()
        }

    }
}