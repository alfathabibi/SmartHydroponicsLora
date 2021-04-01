package org.d2e.smarthydroponic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import org.d2e.smarthydroponic.auth.FirebaseUserLiveData
import org.d2e.smarthydroponic.data.Device

class MainViewModel() : ViewModel(){
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val databaseReference = FirebaseDatabase.getInstance()
    val authState = FirebaseUserLiveData()
    private var _deviceId = MutableLiveData<String>()
    private var _data = MutableLiveData<Device>()

    init {
        getDeviceId()
    }

    private fun getCurrentUser() = firebaseAuth.currentUser
    private fun getDeviceId() {
        databaseReference.getReference("Users/${getCurrentUser()!!.uid}").child("deviceId").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val devId = snapshot.getValue<String>()
                _deviceId.value = devId
                getDeviceData(devId)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun getDeviceData(devId : String?){
        databaseReference.getReference("devices/$devId").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val device = snapshot.getValue<Device>()
                _data.value = device
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    val deviceId : LiveData<String> get() = _deviceId
    val data : LiveData<Device> get() = _data

}