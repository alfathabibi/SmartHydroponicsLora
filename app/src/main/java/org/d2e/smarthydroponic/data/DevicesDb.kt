package org.d2e.smarthydroponic.data

import androidx.lifecycle.LiveData
import com.google.firebase.database.FirebaseDatabase

class DevicesDb private constructor(): DevicesDao {

    companion object{
        private val PATH = "devices/shl1"
        @Volatile
        private var INSTANCE: DevicesDb? = null
        fun getInstance(): DevicesDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = DevicesDb()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    private val db = FirebaseDatabase.getInstance().getReference(PATH)

    override fun getData(): LiveData<Device> {
        return DeviceLiveData(db)
    }

}