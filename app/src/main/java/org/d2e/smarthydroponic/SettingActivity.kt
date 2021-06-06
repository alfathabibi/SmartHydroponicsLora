package org.d2e.smarthydroponic

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_setting.*
import org.d2e.smarthydroponic.auth.ChangeDevIdActivity
import org.d2e.smarthydroponic.auth.LoginActivity
import java.lang.Exception

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

        viewModel.data.observe(this, Observer {
            if (it == null) tvDevIdSetting.text = getString(R.string.device_not_found)
        })

        ivBackSetting.setOnClickListener {
            onBackPressed()
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

        clCD.setOnClickListener {
            Intent(this, ChangeDevIdActivity::class.java).also {
                startActivity(it)
            }
        }

        clHelp.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            val recepients = "d23team2021@gmail.com"
            val cc = "alfath.abibi@gmail.com"
            intent.data = Uri.parse("mailto:")
            intent.type = "message/rfc822"

            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recepients))
            intent.putExtra(Intent.EXTRA_CC, arrayOf(cc))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Help From User Moura")
            intent.putExtra(Intent.EXTRA_TEXT, "")

            try{
                startActivity(Intent.createChooser(intent, "Choose Email Client..."))
            }catch (e: Exception){
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }

        }
    }
}