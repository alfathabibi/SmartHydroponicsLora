package org.d2e.smarthydroponic.data

import androidx.lifecycle.LiveData
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.getValue

class DeviceLiveData (private val db: DatabaseReference) : LiveData<Device>() {
    private val data = Device()

    init {
        value = data
    }

    private val listener = object : ChildEventListener{
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {

        }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            val device = snapshot.getValue<Device>() ?: return

            data.let {
                it.status = device.status
                it.command = device.command
            }
            value = data
        }

        override fun onChildRemoved(snapshot: DataSnapshot) {

        }

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
        }

        override fun onCancelled(error: DatabaseError) {
        }

    }

    override fun onActive() {
        db.addChildEventListener(listener)
    }

    override fun onInactive() {
        db.removeEventListener(listener)
    }
}