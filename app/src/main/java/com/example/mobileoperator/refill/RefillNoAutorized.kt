package com.example.mobileoperator.refill

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.mobileoperator.R
import com.example.mobileoperator.db.MyDbManager

class RefillNoAutorized : AppCompatActivity() {

    val MyDbManager = MyDbManager(this)

    private var phone_number: TextView? = null
    private var value: TextView? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refill_no_autorized)
        getSupportActionBar()?.hide()

        MyDbManager.openDatabase()

        phone_number = findViewById(R.id.phone_number)
        value = findViewById(R.id.value)

    }



    fun refill_next_Click(view: View) {



        var numberOk = false

        val number_list  = MyDbManager.readNewAbonentRequest("number")

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


        if(phone_number?.text?.length !=12 ){
            Toast.makeText(this, "Введите корректный номер телефона", Toast.LENGTH_LONG).show()
        }
        else if(numberOk == false){
            Toast.makeText(this, "Номер не существует.", Toast.LENGTH_LONG).show()
        }
        else if(value?.text?.length == 0 ){
            Toast.makeText(this, "Введите сумму", Toast.LENGTH_LONG).show()
        }
        else if(value?.text?.get(0) == '.'){
            Toast.makeText(this, "Сумма не должна начинаться с точки", Toast.LENGTH_LONG).show()
            value?.text = "0${value?.text}"
        }
        else {

            val balance_list  = MyDbManager.readNewAbonentRequest("balance")

            var indexOfNumber = 0
            indexOfNumber = number_list.indexOf(phone_number?.text.toString())

            val balance = balance_list[indexOfNumber]


            val intent = Intent(this,PayNoAutorized::class.java)
            intent.putExtra(PayNoAutorized.VALUE , value?.text.toString())
            intent.putExtra(PayNoAutorized.NUMBER , phone_number?.text.toString())
            intent.putExtra(PayNoAutorized.BALANCE ,balance)
            startActivity(intent)

        }



    }
}