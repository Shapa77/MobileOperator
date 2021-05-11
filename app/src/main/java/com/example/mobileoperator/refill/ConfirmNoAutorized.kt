package com.example.mobileoperator.refill

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mobileoperator.MainActivity
import com.example.mobileoperator.R

class ConfirmNoAutorized : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_no_autorized)
        getSupportActionBar()?.hide();

    }

    override fun onBackPressed() {}

    fun ok_Click(view: View) {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }


}