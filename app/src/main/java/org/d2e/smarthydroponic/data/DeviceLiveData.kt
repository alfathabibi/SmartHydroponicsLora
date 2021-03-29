package org.d2e.smarthydroponic.data

import androidx.lifecycle.LiveData
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue

class DeviceLiveData (private val db: DatabaseReference) : LiveData<Device>() {
    private val data = Device()

    init {
        value = data
    }

    private val listener = object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            val device = snapshot.getValue<Device>() ?: return
            value = device
        }

        override fun onCancelled(error: DatabaseError) {

        }


    }

    override fun onActive() {
        db.addValueEventListener(listener)
    }

    override fun onInactive() {
        db.removeEventListener(listener)
    }
}