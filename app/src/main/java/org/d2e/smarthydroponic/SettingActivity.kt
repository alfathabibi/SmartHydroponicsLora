package org.d2e.smarthydroponic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_setting.*
import org.d2e.smarthydroponic.auth.LoginActivity

class SettingActivity : AppCompatActivity() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

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
                        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
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
    }
}