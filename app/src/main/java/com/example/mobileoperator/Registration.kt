package com.example.mobileoperator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.mobileoperator.db.MyDbManager

class Registration : AppCompatActivity() {

    private var phone_number: TextView? = null
    private var password: TextView? = null
    private var repeat_password: TextView? = null


    val MyDbManager = MyDbManager(this)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        getSupportActionBar()?.hide();

        phone_number = findViewById(R.id.phone_number)
        password = findViewById(R.id.value)
        repeat_password = findViewById(R.id.repeat_password)

        MyDbManager.openDatabase()




    }

    fun register_button_Click(view: View) {

        var numberOk = false
        var numberOkReg = false



        val number_list  = MyDbManager.readNewAbonentRequest("number")
        val number_list_reg  = MyDbManager.readRegistration("number")



        if(number_list.size != 0) {
            for (i in number_list) {
                if (i == phone_number?.text.toString()) {
                    numberOk = true
                }
            }
        }
        else if(number_list.size == 0){
            numberOk = false
        }


        if(number_list_reg.size == 0){
                    numberOkReg = true
        }
        else{
            for(i in number_list_reg){
                    if(i == phone_number?.text.toString()) {
                        numberOkReg = false
                        break
                    }
                    else{
                        numberOkReg = true
                    }
            }
        }


        if(phone_number?.text.toString() == "" ){
            Toast.makeText(this, "Введите номер телефона", Toast.LENGTH_LONG).show()
        }

        else if(phone_number?.text?.length != 12 ){
            Toast.makeText(this, "Введите корректный номер телефона", Toast.LENGTH_LONG).show()
        }

        else if(numberOk == false){
            Toast.makeText(this, "Номер не существует.", Toast.LENGTH_LONG).show()
            numberOk = false
            numberOkReg = false
        }
        else if(numberOkReg == false){
            Toast.makeText(this, "Номер уже зарегистрирован.", Toast.LENGTH_LONG).show()
            numberOk = false
            numberOkReg = false
        }
        else if(password?.text.toString() == ""){
            Toast.makeText(this, "Введите пароль", Toast.LENGTH_LONG).show()

        }
        else if(repeat_password?.text.toString() == "" && password?.text.toString() != "" ){
            Toast.makeText(this, "Подтверите пароль", Toast.LENGTH_LONG).show()

        }

        else if(password?.text.toString() != repeat_password?.text.toString() ){
            Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_LONG).show()
        }
        else{

            MyDbManager.writeOnRegistration(phone_number?.text.toString(), password?.text.toString())
            Toast.makeText(this, "Регистрация успешна!", Toast.LENGTH_LONG).show()
            numberOk = false
            numberOkReg = false

            val intent = Intent(this,LogIn::class.java)
            startActivity(intent)


        }
    }

    fun home_page_text_Click(view: View){
        MyDbManager.CloseDatabase()
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}