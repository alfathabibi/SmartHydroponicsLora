package org.d2e.smarthydroponic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d2e.smarthydroponic.data.DevicesDao
import org.d2e.smarthydroponic.data.DevicesDb

class TemperatureViewModel (private val db: DevicesDao) : ViewModel(){
    val data = db.getData()

    private val temperatureDb = FirebaseDatabase.getInstance().getReference("devices/shl1/command")

    fun updateMinTemperature(minTemperature: Float){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                temperatureDb.child("minTemp").setValue(minTemperature)
            }
        }
    }

    fun updateMaxTemperature(maxTemperature: Float){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                temperatureDb.child("maxTemp").setValue(maxTemperature)
            }
        }
    }
}