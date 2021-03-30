package org.d2e.smarthydroponic.process

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d2e.smarthydroponic.data.DevicesDao

class NutritionViewModel (private val db: DevicesDao) : ViewModel(){
    val data = db.getData()

    private val temperatureDb = FirebaseDatabase.getInstance().getReference("devices/shl1/command")

    fun updateNutrition(nutrition: Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                temperatureDb.child("nutriSet").setValue(nutrition)
            }
        }
    }
}