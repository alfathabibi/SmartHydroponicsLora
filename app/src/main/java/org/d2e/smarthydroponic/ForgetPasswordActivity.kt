package org.d2e.smarthydroponic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forget_password.*

class ForgetPasswordActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        ivBackForgot.setOnClickListener {
            finish()
        }

        firebaseAuth = FirebaseAuth.getInstance()

        cvBtnForgot.setOnClickListener {

            val email = etEmailForgot.text.toString()

            if (!isValidEmail(email)){
                etEmailForgot.error = getString(R.string.email_required)
                return@setOnClickListener
            }

            progressBarForgot.visibility = View.VISIBLE
            tvBtnForgot.visibility = View.GONE
            firebaseAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener { task ->
                if (task.result?.signInMethods!!.isEmpty()){
                    progressBarForgot.visibility = View.GONE
                    tvBtnForgot.visibility = View.VISIBLE
                    tvResetState.text = getString(R.string.account_not_exist_message)
                }else{
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener {
                        progressBarForgot.visibility = View.GONE
                        tvBtnForgot.visibility = View.VISIBLE
                        if (it.isSuccessful){
                            tvResetState.text = getString(R.string.account_exist_message)
                            etEmailForgot.text.clear()
                        }else{
                            tvResetState.text = it.exception?.message
                        }
                    }
                }
            }
        }
    }

    fun isValidEmail(email : String) : Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}