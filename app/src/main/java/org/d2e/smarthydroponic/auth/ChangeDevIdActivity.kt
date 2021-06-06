package org.d2e.smarthydroponic.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_change_dev_id.*
import org.d2e.smarthydroponic.MainActivity
import org.d2e.smarthydroponic.R

class ChangeDevIdActivity : AppCompatActivity() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val viewModel : ChangeDevIdViewModel by lazy {
        ViewModelProvider(this).get(ChangeDevIdViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_dev_id)

        viewModel.deviceId.observe(this, Observer {
            tvDevId.text = "ID : $it"
        })

        viewModel.data.observe(this, Observer {
            if (it != null) {
                ivSad.visibility = View.GONE
                tvFilterMessage.text = getString(R.string.can_update)
            }
        })

        ivBackFilter.setOnClickListener {
            onBackPressed()
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