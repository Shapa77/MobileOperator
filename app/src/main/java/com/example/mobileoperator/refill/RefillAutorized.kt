package com.example.mobileoperator.refill

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.mobileoperator.R
import com.example.mobileoperator.db.MyDbManager

class RefillAutorized : AppCompatActivity() {


    companion object{
        const val NUMBER = "NUMBER"
    }



    val MyDbManager = MyDbManager(this)


    private var value: TextView? = null
    private var number: TextView? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refill_autorized)
        getSupportActionBar()?.hide()

        MyDbManager.openDatabase()

        value = findViewById(R.id.value)
        number = findViewById(R.id.number)

        val number_value = intent.getStringExtra(NUMBER)

        showNumber(number_value)

    }




    fun refill_next_Click(view: View) {

        val number_value = intent.getStringExtra(NUMBER)
        val number_list  = MyDbManager.readNewAbonentRequest("number")

        if(value?.text?.length == 0  ){
            Toast.makeText(this, "Введите сумму", Toast.LENGTH_LONG).show()
        }
        else if(value?.text?.get(0) == '.'){
            Toast.makeText(this, "Сумма не должна начинаться с точки", Toast.LENGTH_LONG).show()
            value?.text = "0${value?.text}"
        }
        else {

            val balance_list  = MyDbManager.readNewAbonentRequest("balance")

            var indexOfNumber = 0
            indexOfNumber = number_list.indexOf(number_value)

            val balance = balance_list[indexOfNumber]


            val intent = Intent(this,PayAutorized::class.java)
            intent.putExtra(PayAutorized.VALUE , value?.text.toString())
            intent.putExtra(PayAutorized.NUMBER , number_value)
            intent.putExtra(PayAutorized.BALANCE ,balance)
            startActivity(intent)

        }



    }


    fun showNumber(number_value:String?) {

        var count = -1
        var num = ""

        if (number_value != null) {
            for(i in number_value) {
                count++
                if (count == 2 || count == 3 || count == 5 || count == 6 || count == 8 || count == 10 || count == 11) {
                    num += i
                }

                if (count == 4 || count == 7 || count == 9) {
                    num += i
                    num += " "
                }
            }

        }
        number?.text = num
    }


}