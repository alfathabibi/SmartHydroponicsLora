package org.d2e.smarthydroponic

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_aboutus.*

class AboutusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aboutus)

        ivBackAboutus.setOnClickListener {
            finish()
        }

        ivIgDev1.setOnClickListener {
            val webIntent: Intent = Uri.parse("https://www.instagram.com/alfathabibi").let { webpage ->
                Intent(Intent.ACTION_VIEW, webpage)
            }
            startActivity(webIntent)
        }
        ivIgDev2.setOnClickListener {
            val webIntent: Intent = Uri.parse("https://www.instagram.com/fachran_wardi").let { webpage ->
                Intent(Intent.ACTION_VIEW, webpage)
            }
            startActivity(webIntent)
        }
        ivGitDev1.setOnClickListener {
            val webIntent: Intent = Uri.parse("https://www.github.com/alfathabibi").let { webpage ->
                Intent(Intent.ACTION_VIEW, webpage)
            }
            startActivity(webIntent)
        }
        ivDbDev2.setOnClickListener {
            val webIntent: Intent = Uri.parse("https://www.dribble.com/fachranwardi").let { webpage ->
                Intent(Intent.ACTION_VIEW, webpage)
            }
            startActivity(webIntent)
        }
    }
}