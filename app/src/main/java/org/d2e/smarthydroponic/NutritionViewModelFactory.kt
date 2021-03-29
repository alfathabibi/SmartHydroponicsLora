package org.d2e.smarthydroponic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d2e.smarthydroponic.data.DevicesDao

class NutritionViewModelFactory (private val dataSource: DevicesDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NutritionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NutritionViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unable to construct ViewModel")
    }

}