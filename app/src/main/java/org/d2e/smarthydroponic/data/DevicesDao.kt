package org.d2e.smarthydroponic.data

import androidx.lifecycle.LiveData

interface DevicesDao {
    fun getData(): LiveData<Device>
}