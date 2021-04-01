package org.d2e.smarthydroponic.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import org.d2e.smarthydroponic.ForgetPasswordActivity
import org.d2e.smarthydroponic.MainActivity
import org.d2e.smarthydroponic.R
import org.d2e.smarthydroponic.process.PhActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firebaseAuth = FirebaseAuth.getInstance()

        val viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        viewModel.authState.observe(this, Observer {
            if (it != null){
                Intent(this, MainActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        })

        cvBtnLogin.setOnClickListener {
            val email = etEmailLogin.text.toString()
            val password = etPasswordLogin.text.toString()

            if (!isValidEmail(email)){
                etEmailLogin.error = getString(R.string.email_required)
                return@setOnClickListener
            }

            if (password.isEmpty()){
                etPasswordLogin.error = getString(R.string.password_required)
                return@setOnClickListener
            }

            login(email, password)
        }

        tvSignup.setOnClickListener {
            Intent(this, RegisterActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        tvForgotPassword.setOnClickListener {
            Intent(this, ForgetPasswordActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun login(email: String, password: String) {
        progressBarLogin.visibility = View.VISIBLE
        tvBtnLogin.visibility = View.GONE

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful){
                Intent(this, MainActivity::class.java).also {
                    startActivity(it)
                    progressBarLogin.visibility = View.GONE
                    tvBtnLogin.visibility = View.VISIBLE
                }
                finish()
            }else{
                Toast.makeText(this, it.exception!!.message, Toast.LENGTH_SHORT).show()
                progressBarLogin.visibility = View.GONE
                tvBtnLogin.visibility = View.VISIBLE
                return@addOnCompleteListener
            }
        }
    }

    fun isValidEmail(email : String) : Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}