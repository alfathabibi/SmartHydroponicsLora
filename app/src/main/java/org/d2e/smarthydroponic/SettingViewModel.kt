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
import org.d2e.smarthydroponic.data.Device
import org.d2e.smarthydroponic.data.User

class SettingViewModel : ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val databaseReference = FirebaseDatabase.getInstance()

    private var _user = MutableLiveData<User>()
    private var _data = MutableLiveData<Device>()

    private fun getCurrentUser() = firebaseAuth.currentUser

    init {
        getUserInfor()
    }

    private fun getUserInfor() {
        databaseReference.getReference("Users/${getCurrentUser()!!.uid}").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue<User>()
                _user.value = user
                getDeviceData(user?.deviceId)
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

    val user : LiveData<User> get() = _user
    val data : LiveData<Device> get() = _data
}