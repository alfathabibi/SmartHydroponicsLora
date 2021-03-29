package org.d2e.smarthydroponic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d2e.smarthydroponic.data.DevicesDao

class PhViewModelFactory (private val dataSource: DevicesDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PhViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unable to construct ViewModel")
    }

}