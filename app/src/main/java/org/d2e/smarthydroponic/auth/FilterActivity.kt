package org.d2e.smarthydroponic.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_filter.*
import kotlinx.android.synthetic.main.activity_main.*
import org.d2e.smarthydroponic.MainActivity
import org.d2e.smarthydroponic.MainViewModel
import org.d2e.smarthydroponic.R
import org.d2e.smarthydroponic.SettingActivity

class FilterActivity : AppCompatActivity() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val viewModel : FilterViewModel by lazy {
        ViewModelProvider(this).get(FilterViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        viewModel.deviceId.observe(this, Observer {
            tvDevId.text = "ID : $it"
        })

        ivSettingFilter.setOnClickListener {
            Intent(this, SettingActivity::class.java).also {
                startActivity(it)
            }
        }

        cvBtnUpdateDeviceId.setOnClickListener {
            val deviceId = etNewDeviceId.text.toString()
            if (deviceId.isEmpty()){
                etNewDeviceId.error = getString(R.string.devicid_required)
                return@setOnClickListener
            }
            viewModel.updateDeviceId(deviceId)
            Toast.makeText(this, getString(R.string.update_successfull), Toast.LENGTH_SHORT).show()
            etNewDeviceId.text.clear()
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }
}