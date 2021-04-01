package org.d2e.smarthydroponic.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d2e.smarthydroponic.data.Device

class FilterViewModel : ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val databaseReference = FirebaseDatabase.getInstance()
    private var _deviceId = MutableLiveData<String>()

    init {
        getDeviceId()
    }

    private fun getCurrentUser() = firebaseAuth.currentUser
    private fun getDeviceId() {
        databaseReference.getReference("Users/${getCurrentUser()!!.uid}").child("deviceId").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val devId = snapshot.getValue<String>()
                _deviceId.value = devId
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun updateDeviceId(devId: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                databaseReference.getReference("Users/${getCurrentUser()!!.uid}").child("deviceId").setValue(devId)
            }
        }
    }

    val deviceId : LiveData<String> get() = _deviceId
}