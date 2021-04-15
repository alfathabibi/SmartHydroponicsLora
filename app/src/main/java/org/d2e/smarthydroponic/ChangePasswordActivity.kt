package org.d2e.smarthydroponic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_change_password.*

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseUser = firebaseAuth.currentUser!!

        ivBackCP.setOnClickListener {
            finish()
        }

        cvBtnSaveNewPassword.setOnClickListener {
            val currentPassword = etCurrentPassword.text.toString()
            val newPassword = etNewPassword.text.toString()
            val cNewPassword = etCNewPassword.text.toString()

            if (currentPassword.isEmpty()){
                etCurrentPassword.error = getString(R.string.password_required)
                return@setOnClickListener
            }

            if (newPassword.isEmpty()){
                etNewPassword.error = getString(R.string.password_required)
                return@setOnClickListener
            }

            if (newPassword.length < 6){
                etNewPassword.error = getString(R.string.password_less)
                return@setOnClickListener
            }

            if (cNewPassword != newPassword){
                etCNewPassword.error = getString(R.string.password_different)
                return@setOnClickListener
            }

            changePassword(currentPassword, newPassword, cNewPassword)

        }

    }

    private fun changePassword(currentPassword: String, newPassword: String, cNewPassword: String) {
        progressBarChange.visibility = View.VISIBLE
        tvBtnSaveNewPassword.visibility = View.GONE
        val credential = EmailAuthProvider.getCredential(firebaseUser.email!!, currentPassword)
        firebaseUser.reauthenticate(credential).addOnCompleteListener {
            if (it.isSuccessful){
                firebaseUser.updatePassword(newPassword).addOnCompleteListener {update ->
                    if (update.isSuccessful){
                        finish()
                        Toast.makeText(this, getString(R.string.update_successfull), Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else{
                progressBarChange.visibility = View.GONE
                tvBtnSaveNewPassword.visibility = View.VISIBLE
                Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}