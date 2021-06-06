package org.d2e.smarthydroponic.process

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_nutrition.*
import org.d2e.smarthydroponic.R

class NutritionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nutrition)

        ivBackNutrition.setOnClickListener {
            finish()
        }

        val viewModel = ViewModelProvider(this).get(NutritionViewModel::class.java)

        viewModel.data.observe(this, Observer {
            tvSetNutrition.text = getString(R.string.current_nutrition_set, it.command.nutriSet)
            tvCurrentNutrition.text = getString(R.string.current_nutrition, it.status.tdsValue)
        })

        cvBtnSetNutrition.setOnClickListener {
            val nutritionSet = etMinNutrition.text.toString().trim()
            if (nutritionSet.isEmpty()){
                etMinNutrition.error = getString(R.string.please_fill_the_blank)
                etMinNutrition.requestFocus()
                return@setOnClickListener
            }
            if (nutritionSet.toInt() < 0){
                etMinNutrition.error = getString(R.string.nutrition_error)
                etMinNutrition.requestFocus()
                return@setOnClickListener
            }

            viewModel.updateNutrition(nutritionSet.toInt())
            Toast.makeText(this, getString(R.string.update_successfull), Toast.LENGTH_SHORT).show()
            etMinNutrition.text.clear()
        }
    }
}