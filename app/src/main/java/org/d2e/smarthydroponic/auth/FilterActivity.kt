package org.d2e.smarthydroponic.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_filter.*
import org.d2e.smarthydroponic.MainActivity
import org.d2e.smarthydroponic.R

class FilterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

    }
}