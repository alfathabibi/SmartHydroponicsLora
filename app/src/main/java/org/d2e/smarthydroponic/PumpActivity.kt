package org.d2e.smarthydroponic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_ph.*
import kotlinx.android.synthetic.main.activity_pump.*
import org.d2e.smarthydroponic.data.DevicesDb

class PumpActivity : AppCompatActivity() {

    private val viewModel: PumpViewModel by lazy {
        val factory = PumpViewModelFactory(DevicesDb.getInstance())
        ViewModelProvider(this, factory).get(PumpViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pump)

        ivBackPump.setOnClickListener {
            finish()
        }

        viewModel.data.observe(this, Observer {
            if (it.command.flowPump == 1){
                swPump.isChecked = true
            }else{
                swPump.isChecked = false
            }
        })

        swPump.setOnClickListener {
            if (swPump.isChecked){
                viewModel.updatePump(1)
                Toast.makeText(this, getString(R.string.update_successfull), Toast.LENGTH_SHORT).show()
            }else{
                viewModel.updatePump(0)
                Toast.makeText(this, getString(R.string.update_successfull), Toast.LENGTH_SHORT).show()
            }
        }


    }
}