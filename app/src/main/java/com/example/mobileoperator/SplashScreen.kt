package com.example.mobileoperator

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {


    lateinit var iv_note : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        getSupportActionBar()?.hide();
        iv_note = findViewById(R.id.iv_note)



        iv_note.alpha = 0f
        iv_note.animate().setDuration(1500).alpha(1f).withEndAction{
            val intent = Intent(this ,MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in ,android.R.anim.fade_out)
            finish()

        }

    }

}