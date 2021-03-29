package org.d2e.smarthydroponic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_nutrition.*
import org.d2e.smarthydroponic.data.DevicesDb

class NutritionActivity : AppCompatActivity() {

    private val viewModel: NutritionViewModel by lazy {
        val factory = NutritionViewModelFactory(DevicesDb.getInstance())
        ViewModelProvider(this, factory).get(NutritionViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nutrition)

        ivBackNutrition.setOnClickListener {
            finish()
        }

        viewModel.data.observe(this, Observer {
            tvCurrentNutrition.text = getString(R.string.current_nutrition_set, it.command.nutriSet)
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