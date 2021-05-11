package com.example.mobileoperator.new_abonent

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileoperator.MainActivity
import com.example.mobileoperator.R


class NewAbonentYes : AppCompatActivity() {

    private var your_number: TextView? = null

    companion object{
        const val NEW_NUMBER = "NEW_NUMBER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_abonent_yes)
        supportActionBar?.hide();

        your_number = findViewById(R.id.your_number)

        val number = intent.getStringExtra(NEW_NUMBER)
        your_number?.text = number.toString()


    }





    private fun setClipboard(context: Context ,text: String) {
        val clipboard = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Номер скопирован" ,text)
        clipboard.setPrimaryClip(clip)
    }

    fun ok_button_Click(view: View) {
        val intent = Intent(this ,MainActivity::class.java)
        startActivity(intent)
    }

    fun copy_Click(view: View) {

        setClipboard(this, your_number?.text.toString())
        Toast.makeText(this, "Номер скопирован.", Toast.LENGTH_LONG).show()

    }


}