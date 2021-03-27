package org.d2e.smarthydroponic

import androidx.lifecycle.ViewModel
import org.d2e.smarthydroponic.data.DevicesDao

class MainViewModel(private val db: DevicesDao) : ViewModel(){
    val data = db.getData()
}