package com.example.mobileoperator.refill

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mobileoperator.MainMenu
import com.example.mobileoperator.R
import com.example.mobileoperator.db.MyDbManager

class ConfirmAutorized : AppCompatActivity() {


    companion object{
        const val NUMBER = "NUMBER"
        const val BALANCE_AFTER_REFILL =  "BALANCE"
    }

    val MyDbManager = MyDbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_autorized)
        getSupportActionBar()?.hide();

        MyDbManager.openDatabase()


    }

    override fun onBackPressed() {}


    fun ok_Click(view: View) {

        val balance = intent.getStringExtra(BALANCE_AFTER_REFILL)
        val number = intent.getStringExtra(NUMBER)

        val intent = Intent(this,MainMenu::class.java)
        intent.putExtra(MainMenu.BALANCE_AFTER_REFILL , "$balance")
        intent.putExtra(MainMenu.NUMBER , "$number")

        startActivity(intent)

    }







}