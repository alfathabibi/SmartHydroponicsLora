package org.d2e.smarthydroponic.process

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_ph.*
import org.d2e.smarthydroponic.R

class PhActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ph)

        ivBackPh.setOnClickListener {
            finish()
        }


        val viewModel = ViewModelProvider(this).get(PhViewModel::class.java)

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
            if (pHSet.toDouble() < 0 || pHSet.toFloat() > 14){
                etMinPh.setError(getString(R.string.ph_error))
                etMinPh.requestFocus()
                return@setOnClickListener
            }

            viewModel.updatePh(pHSet.toDouble())
            Toast.makeText(this, getString(R.string.update_successfull), Toast.LENGTH_SHORT).show()
            etMinPh.text.clear()
        }
    }
}