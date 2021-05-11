package com.example.mobileoperator

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mobileoperator.db.MyDbManager
import com.example.mobileoperator.tariffs.Tariffs

class Menu : AppCompatActivity() {


    companion object{
        const val NUMBER = "NUMBER"
        const val TARIFF = "TARIFF"
        const val BALANCE = "BALANCE"
    }


    val MyDbManager = MyDbManager(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        getSupportActionBar()?.hide()


        MyDbManager.openDatabase()


    }

    fun tariffs_Click(view: View) {


        val number_value = intent.getStringExtra(NUMBER)
        val tariff_value = intent.getStringExtra(TARIFF)
        val balance_value = intent.getStringExtra(BALANCE)

        val intent = Intent(this,Tariffs::class.java)
        intent.putExtra(Tariffs.NUMBER, number_value)
        intent.putExtra(Tariffs.TARIFF, tariff_value)
        intent.putExtra(Tariffs.BALANCE, balance_value)
        startActivity(intent)


    }

    fun help_Click(view: View) {

        AlertDialog.Builder(this).apply {
            setTitle("Помощь")
            setMessage("Спасибо! В ближайшее время с Вами свяжется наш специалист. Пожалуйста, ожидайте.")
            setNegativeButton("ОК"){_, _ ->
            }
            setCancelable(true)
        }.create().show()



    }
    fun dop_Click(view: View) {
        AlertDialog.Builder(this).apply {
            setTitle("Услуги")
            setMessage("По Вашему номеру нет доступных дополнительных услуг!")
            setNegativeButton("Хорошо"){_, _ ->
            }
            setCancelable(true)
        }.create().show()

    }
    fun exit_Click(view: View) {

        val intent = Intent(this,LogIn::class.java)

        AlertDialog.Builder(this).apply {
            setTitle("Подтверждение")
            setMessage("Вы уверены, что хотите выйти из аккаунта?")
            setPositiveButton("Да") { _, _ ->

                startActivity(intent)
                Values.isLogIn = false
                val editor = Values.pref?.edit()
                editor?.putBoolean("login", Values.isLogIn)
                editor?.apply()

            }
            setNegativeButton("Нет"){_, _ ->
            }
            setCancelable(true)
        }.create().show()

    }
}