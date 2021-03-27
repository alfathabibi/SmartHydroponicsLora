package org.d2e.smarthydroponic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d2e.smarthydroponic.data.DevicesDao


class MainViewModelFactory(private val dataSource: DevicesDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unable to construct ViewModel")
    }

}