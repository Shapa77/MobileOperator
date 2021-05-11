package com.example.mobileoperator

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileoperator.Values.pref
import com.example.mobileoperator.db.MyDbManager
import com.example.mobileoperator.new_abonent.NewAbonentRequest

import com.example.mobileoperator.refill.RefillNoAutorized


class MainActivity : AppCompatActivity() {

    companion object{
        const val NUMBER = "NUMBER"
        const val BALANCE = "BALANCE"
    }



    private var get_number: View? = null

    val MyDbManager = MyDbManager(this)






    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.hide()
        pref = getSharedPreferences("TABLE", MODE_PRIVATE)


            get_number = findViewById(R.id.get_number)
            MyDbManager.openDatabase()
            FirstUpdate()




        Values.isLogIn = pref?.getBoolean("login",true)!!

        if(Values.isLogIn){

            val number = pref?.getString("number", "")

            val number_list  = MyDbManager.readNewAbonentRequest("number")
            val balance_list  = MyDbManager.readNewAbonentRequest("balance")

            val index = number_list.indexOf(number)

            val balance = balance_list[index]
            val intent = Intent(this,MainMenu::class.java)
            intent.putExtra(MainMenu.NUMBER, number)
            intent.putExtra(MainMenu.BALANCE, balance)
            startActivity(intent)
        }


    }

    override fun onBackPressed() {
        AlertDialog.Builder(this).apply {
            setTitle("Подтверждение")
            setMessage("Вы уверены, что хотите выйти из программы?")
            setPositiveButton("Да") { _, _ ->
                super.onBackPressed()
            }
            setNegativeButton("Нет"){_, _ ->
            }
            setCancelable(true)
        }.create().show()
    }





    fun FirstUpdate() {
        val FirstUpdate = getSharedPreferences("Upgrate" ,MODE_PRIVATE).getBoolean("FirstUpdate" ,true)
        if (FirstUpdate) {
            MyDbManager.onUpgrate()
            val editor = pref?.edit()
            editor?.putBoolean("login", Values.isLogIn)
            editor?.apply()
            getSharedPreferences("Upgrate" ,MODE_PRIVATE).edit().putBoolean("FirstUpdate" ,false).apply()
        }
    }




    fun get_num_click(view: View){

        val intent_ = Intent(this ,NewAbonentRequest::class.java)
        startActivity(intent_)
    }

    fun registration_click(view: View){
        val intent_ = Intent(this ,Registration::class.java)
        startActivity(intent_)
    }

    fun logIn_Click(view: View) {
        val intent_ = Intent(this ,LogIn::class.java)
        startActivity(intent_)


    }

    fun Refil_Click(view: View) {

        val intent = Intent(this,RefillNoAutorized::class.java)
        startActivity(intent)

    }





}