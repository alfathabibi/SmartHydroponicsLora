package org.d2e.smarthydroponic.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*
import org.d2e.smarthydroponic.MainActivity
import org.d2e.smarthydroponic.R
import org.d2e.smarthydroponic.process.PhActivity
import java.util.*
import kotlin.collections.HashMap

class RegisterActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        firebaseAuth = FirebaseAuth.getInstance()
                
        tvLoginHere.setOnClickListener {
            Intent(this, LoginActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        cvBtnRegister.setOnClickListener {
            val email = etEmailRegister.text.toString()
            val deviceId = etDeviceId.text.toString()
            val password = etPasswordRegister.text.toString()
            val cPassword = etConfirmPasswordRegister.text.toString()
            val name = etNameRegister.text.toString()

            if(!isValidEmail(email)){
                etEmailRegister.error = (getString(R.string.email_required))
                return@setOnClickListener
            }
            if (deviceId.isEmpty()){
                etDeviceId.error = getString(R.string.devicid_required)
                return@setOnClickListener
            }
            if (password.isEmpty()){
                etPasswordRegister.error = getString(R.string.password_required)
                return@setOnClickListener
            }
            if (password.length < 6){
                etPasswordRegister.error = getString(R.string.password_less)
                return@setOnClickListener
            }
            if (password != cPassword){
                etConfirmPasswordRegister.error = getString(R.string.password_different)
                return@setOnClickListener
            }

            if  (name.isEmpty()){
                etNameRegister.error = getString(R.string.name_required)
                return@setOnClickListener
            }

            register(name,email, deviceId, password)

        }
    }

    private fun register(name: String, email: String, deviceId: String, password: String) {
        progressBar.visibility = View.VISIBLE
        tvBtnSignup.visibility = View.GONE

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful){
                val rUser = firebaseAuth.currentUser
                val userId = rUser!!.uid
                databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId)
                val hashMap = HashMap<String,String>()
                hashMap.put("userId",userId)
                hashMap.put("email",email)
                hashMap.put("deviceId",deviceId)
                hashMap.put("name",name)

                databaseReference.setValue(hashMap).addOnCompleteListener {
                    if (it.isSuccessful){
                        progressBar.visibility = View.GONE
                        tvBtnSignup.visibility = View.VISIBLE
                        val intent = Intent(this, MainActivity::class.java)
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this, Objects.requireNonNull(it.exception)?.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                progressBar.visibility = View.GONE
                tvBtnSignup.visibility = View.VISIBLE
                Toast.makeText(this, Objects.requireNonNull(it.exception)!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun isValidEmail(email : String) : Boolean {
           return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}