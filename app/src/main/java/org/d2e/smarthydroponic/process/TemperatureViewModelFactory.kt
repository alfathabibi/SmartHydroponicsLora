package org.d2e.smarthydroponic.process

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d2e.smarthydroponic.data.DevicesDao

class TemperatureViewModelFactory(private val dataSource: DevicesDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TemperatureViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TemperatureViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unable to construct ViewModel")
    }

}