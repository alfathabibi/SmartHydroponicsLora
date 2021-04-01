package org.d2e.smarthydroponic.process

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_pump.*
import org.d2e.smarthydroponic.R

class PumpActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var user: FirebaseUser
    private lateinit var deviceId : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pump)

        ivBackPump.setOnClickListener {
            finish()
        }

        val viewModel = ViewModelProvider(this).get(PumpViewModel::class.java)

        viewModel.data.observe(this, Observer {
            swPump.isChecked = it.command.flowPump == 1
        })

        swPump.setOnClickListener {
            if (swPump.isChecked){
                viewModel.updatePump(1)
                Toast.makeText(this, getString(R.string.update_successfull), Toast.LENGTH_SHORT).show()
            }else{
                viewModel.updatePump(0)
                Toast.makeText(this, getString(R.string.update_successfull), Toast.LENGTH_SHORT).show()
            }
        }


    }
}