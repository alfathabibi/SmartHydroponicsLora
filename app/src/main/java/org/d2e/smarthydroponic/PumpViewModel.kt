package org.d2e.smarthydroponic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d2e.smarthydroponic.data.DevicesDao

class PumpViewModel (private val db: DevicesDao) : ViewModel(){
    val data = db.getData()

    private val pumpDb = FirebaseDatabase.getInstance().getReference("devices/shl1/command")

    fun updatePump(pumpSet: Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                pumpDb.child("flowPump").setValue(pumpSet)
            }
        }
    }
}