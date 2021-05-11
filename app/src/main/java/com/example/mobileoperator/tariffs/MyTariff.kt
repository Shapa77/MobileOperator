package com.example.mobileoperator.tariffs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.mobileoperator.R
import com.example.mobileoperator.db.MyDbManager

class MyTariff : AppCompatActivity() {


    companion object{
        const val NUMBER = "NUMBER"
        const val TARIFF = "TARIFF"
    }



    private var number: TextView? = null
    private var price: TextView? = null
    private var costs: TextView? = null
    private var update: TextView? = null
    private var mb_text: TextView? = null
    private var sms_text: TextView? = null
    private var min_text: TextView? = null
    private var tariff: TextView? = null



    val MyDbManager = MyDbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_tariff)
        getSupportActionBar()?.hide()


        number = findViewById(R.id.number_text)
        costs = findViewById(R.id.numeral_text)
        update = findViewById(R.id.update_text)
        mb_text = findViewById(R.id.mb_text)
        sms_text = findViewById(R.id.sms_text)
        min_text = findViewById(R.id.min_text)
        tariff = findViewById(R.id.tariff_text)
        price = findViewById(R.id.price_text)

        MyDbManager.openDatabase()


        val number_value = intent.getStringExtra(NUMBER)
        showDescriptionTariff()
        showNumber(number_value)


    }


    fun showDescriptionTariff(){

        val tariff_value = intent.getStringExtra(TARIFF)
        tariff?.text = "Тариф: $tariff_value"


        if(tariff_value == "Mobile Operator Gold XL"){

            mb_text?.text = "10 ГБ"
            sms_text?.text = "100 шт"
            min_text?.text = "200 мин"
            price?.text = "150₴"

        }
        else if(tariff_value == "Mobile Operator MAX Energy" ){

            mb_text?.text = "4 ГБ"
            sms_text?.text = "500 шт"
            min_text?.text = "300 мин"
            price?.text = "120₴"

        }
        else if(tariff_value == "Mobile Operator MaxNet Pro" ){

            mb_text?.text = "20 ГБ"
            sms_text?.text = "30 шт"
            min_text?.text = "60 мин"
            price?.text = "180₴"

        }
        else if(tariff_value == "Mobile Operator Smart 4G" ){

            mb_text?.text = "8 ГБ"
            sms_text?.text = "120 шт"
            min_text?.text = "500 мин"
            price?.text = "130₴"

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