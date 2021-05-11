package com.example.mobileoperator.refill

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.mobileoperator.MainActivity
import com.example.mobileoperator.R
import com.example.mobileoperator.Values
import com.example.mobileoperator.db.MyDbManager

class PayNoAutorized : AppCompatActivity() {


    companion object{
        const val VALUE = "VALUE"
        const val NUMBER = "NUMBER"
        const val BALANCE =  "BALANCE"
    }

    var card_number:TextView? = null
    var month:TextView? = null
    var year:TextView? = null
    var cvv:TextView? = null


    var isValidate = false

    val MyDbManager = MyDbManager(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_no_autorized)
        getSupportActionBar()?.hide();

        MyDbManager.openDatabase()

        card_number = findViewById(R.id.card_number)
        month = findViewById(R.id.month2)
        year = findViewById(R.id.year2)
        cvv = findViewById(R.id.cvv)



    }

    fun confirm_Click(view: View) {

        if(card_number?.text.toString() == "" || month?.text.toString() == "" || cvv?.text.toString() == "" || year?.text.toString() == ""){
            Toast.makeText(this, "Заполните все данные", Toast.LENGTH_LONG).show()
        }
        else if(card_number?.text?.length !=16){
            Toast.makeText(this, "Введите корректный номер карты", Toast.LENGTH_LONG).show()
        }
        else if(year?.text?.length !=2 || month?.text?.length !=2 ){
            Toast.makeText(this, "Введите корректный срок действия", Toast.LENGTH_LONG).show()
        }

        else if((month?.text?.get(0) == '0' || year?.text?.get(0) == '0') && !isValidate ){

            var list = emptyArray<String>()
            for(i in 1..9 ){
                list += "0$i"
            }

            for(i in list){
                if(month?.text?.get(0).toString() + month?.text?.get(1).toString() == i) {

                    isValidate = true
                    confirm_Click(view)
                    break
                }
                else if(year?.text?.get(0).toString() + year?.text?.get(1).toString() == i ||
                    month?.text?.get(0).toString() + month?.text?.get(1).toString() == "00" ||
                    year?.text?.get(0).toString() + year?.text?.get(1).toString() == "00" ){

                    isValidate = false
                    Toast.makeText(this, "Введите корректный срок действия", Toast.LENGTH_LONG).show()
                    break

                }
            }
        }

        else if((month?.text?.get(0) != '0' || year?.text?.get(0) != '0') && !isValidate ){
            isValidate = true
            confirm_Click(view)
        }


        else if(((month?.text?.get(0).toString() + month?.text?.get(1).toString()).toInt() > 12 ) && isValidate){
            Toast.makeText(this, "Введите корректный срок действия", Toast.LENGTH_LONG).show()
            isValidate = false
        }

        else if(((month?.text?.get(0).toString() + month?.text?.get(1).toString()).toInt() < Values.month.toString().toInt() &&
                    (year?.text?.get(0).toString() + year?.text?.get(1).toString()).toInt() <= Values.year.toString().toInt() && isValidate )){

            Toast.makeText(this, "Карта не действительна.", Toast.LENGTH_LONG).show()

        }

        else if(((year?.text?.get(0).toString() + year?.text?.get(1).toString()).toInt() < Values.year.toString().toInt()) && isValidate){
            Toast.makeText(this, "Карта не действительна.", Toast.LENGTH_LONG).show()
            isValidate = false
        }

        else if(cvv?.text?.length != 3){
            Toast.makeText(this, "CVV код должен содержать три цифры", Toast.LENGTH_LONG).show()
            isValidate = false
        }
        else {

            val value = intent.getStringExtra(VALUE)
            val number = intent.getStringExtra(NUMBER)
            val balance = intent.getStringExtra(BALANCE)


            var new_balance = balance?.toDouble()!! + value?.toDouble()!!

            val number_list = MyDbManager.readNewAbonentRequest("number")

            var indexOfNumber = 0
            indexOfNumber = number_list.indexOf(number)

            if(new_balance > 999999){
                Toast.makeText(this, "Пополнение невозможно. Превышен максимальный баланс.", Toast.LENGTH_LONG).show()
                val intent = Intent(this ,MainActivity::class.java)
                startActivity(intent)
            }
            else{
                MyDbManager.Refill("$new_balance" ,indexOfNumber + 1)
                val intent = Intent(this ,ConfirmNoAutorized::class.java)
                startActivity(intent)
            }



        }



    }
}