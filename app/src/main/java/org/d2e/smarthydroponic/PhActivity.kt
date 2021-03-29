package org.d2e.smarthydroponic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_nutrition.*
import kotlinx.android.synthetic.main.activity_ph.*
import org.d2e.smarthydroponic.data.DevicesDb

class PhActivity : AppCompatActivity() {
    private val viewModel: PhViewModel by lazy {
        val factory = PhViewModelFactory(DevicesDb.getInstance())
        ViewModelProvider(this, factory).get(PhViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ph)

        ivBackPh.setOnClickListener {
            finish()
        }

        viewModel.data.observe(this, Observer {
            tvCurrentPh.text = getString(R.string.current_ph_set, it.command.phSet)
        })

        cvBtnSetpH.setOnClickListener {
            val pHSet = etMinPh.text.toString().trim()
            if (pHSet.isEmpty()){
                etMinPh.error = getString(R.string.please_fill_the_blank)
                etMinPh.requestFocus()
                return@setOnClickListener
            }
            if (pHSet.toFloat() < 0 || pHSet.toFloat() > 14){
                etMinPh.setError(getString(R.string.ph_error))
                etMinPh.requestFocus()
                return@setOnClickListener
            }

            viewModel.updateNutrition(pHSet.toFloat())
            Toast.makeText(this, getString(R.string.update_successfull), Toast.LENGTH_SHORT).show()
            etMinPh.text.clear()
        }
    }
}