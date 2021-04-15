package org.d2e.smarthydroponic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_setting.*
import org.d2e.smarthydroponic.auth.LoginActivity

class SettingActivity : AppCompatActivity() {

    private val viewModel : SettingViewModel by lazy {
        ViewModelProvider(this).get(SettingViewModel::class.java)
    }

    private val firebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)


        viewModel.user.observe(this, Observer {
            if (it != null){
                tvNameAccount.text = it.name
                tvEmailSetting.text = it.email
                tvDevIdSetting.text = it.deviceId
            }
        })

        ivBackSetting.setOnClickListener {
            finish()
        }

        clLogOut.setOnClickListener {
            val builder = AlertDialog.Builder(this@SettingActivity)
            builder.setMessage(getString(R.string.confirm_logout))
                .setCancelable(false)
                .setPositiveButton("Yes"){dialog, id ->
                    firebaseAuth.signOut()
                    Intent(applicationContext, LoginActivity::class.java).also {
                        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(it)
                        finish()
                    }
                }
                .setNegativeButton("No"){ dialog, id->
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
        }

        clCp.setOnClickListener {
            Intent(this, ChangePasswordActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}