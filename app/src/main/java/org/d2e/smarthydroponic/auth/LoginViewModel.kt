package org.d2e.smarthydroponic.auth

import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    val authState = FirebaseUserLiveData()
}