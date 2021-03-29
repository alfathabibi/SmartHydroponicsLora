package org.d2e.smarthydroponic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d2e.smarthydroponic.data.DevicesDao

class PhViewModel (private val db: DevicesDao) : ViewModel(){
    val data = db.getData()

    private val pHDb = FirebaseDatabase.getInstance().getReference("devices/shl1/command")

    fun updateNutrition(phSet: Float){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                pHDb.child("phSet").setValue(phSet)
            }
        }
    }
}