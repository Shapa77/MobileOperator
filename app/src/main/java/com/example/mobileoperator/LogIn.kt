package com.example.mobileoperator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import com.example.mobileoperator.Values.pref
import com.example.mobileoperator.db.MyDbManager

class LogIn : AppCompatActivity() {




    val MyDbManager = MyDbManager(this)

    private var phone_number: TextView? = null
    private var password: TextView? = null
    private var checkBox: CheckBox? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        getSupportActionBar()?.hide()
        pref = getSharedPreferences("TABLE", MODE_PRIVATE)

        phone_number = findViewById(R.id.phone_number)
        password = findViewById(R.id.value)
        checkBox = findViewById(R.id.saveData_checkBox)
        MyDbManager.openDatabase()

        Values.save_num_and_pass = pref?.getBoolean("checked",false)!!

        if(Values.save_num_and_pass){
            checkBox?.isChecked = true
            phone_number?.text = pref?.getString("number","")
            password?.text = pref?.getString("password","")
        }



        checkBox?.setOnCheckedChangeListener{ _ ,isChecked ->
            if (isChecked){
                Values.save_num_and_pass = true
                val editor = pref?.edit()
                editor?.putBoolean("checked", Values.save_num_and_pass)
                editor?.apply()
            }
            else if(!isChecked){
                Values.save_num_and_pass = false
                val editor = pref?.edit()
                editor?.putBoolean("checked", Values.save_num_and_pass)
                editor?.apply()
            }
        }
    }





    fun logIn_button_Click(view: View) {

        var numberOk = false
        var indexOfNumber: Int = 0

        val number_list_reg  = MyDbManager.readRegistration("number")
        val password_list_reg  = MyDbManager.readRegistration("password")


        if(number_list_reg.size == 0){
            numberOk = false
        }
        else{
            for(i in number_list_reg){
                if(i == phone_number?.text.toString()) {
                    numberOk = true
                    break
                }
                else{
                    numberOk = false
                }
            }
        }
        if(phone_number?.text.toString() == "" ||  password?.text.toString() == "" ){
            Toast.makeText(this, "Введите данные", Toast.LENGTH_LONG).show()
        }
        else if(numberOk == true) {
            indexOfNumber = number_list_reg.indexOf(phone_number?.text.toString())

            if(phone_number?.text.toString() == number_list_reg[indexOfNumber] && password?.text.toString() == password_list_reg[indexOfNumber] ){

                val balance_list  = MyDbManager.readNewAbonentRequest("balance")

                val intent = Intent(this,MainMenu::class.java)
                intent.putExtra(MainMenu.NUMBER, phone_number?.text.toString())
                intent.putExtra(MainMenu.BALANCE, balance_list[indexOfNumber])
                intent.putExtra(MainMenu.BALANCE_AFTER_REFILL, balance_list[indexOfNumber])
                startActivity(intent)
                Values.isLogIn = true
                val editor = pref?.edit()
                editor?.putString("number", phone_number?.text.toString())
                editor?.putString("password", password?.text.toString())
                editor?.putBoolean("login", Values.isLogIn)
                editor?.apply()

            }
            else{
                Toast.makeText(this, "Неверный номер или пароль", Toast.LENGTH_LONG).show()
            }


        }

        else if (numberOk == false){
            Toast.makeText(this, "Номер не зарегистрирован", Toast.LENGTH_LONG).show()
        }

    }

    override fun onBackPressed() {

    }

    fun home_page_text_Click(view: View){
        MyDbManager.CloseDatabase()
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }







}