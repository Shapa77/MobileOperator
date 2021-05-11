package com.example.mobileoperator.tariffs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.mobileoperator.R
import com.example.mobileoperator.refill.RefillAutorized

class ChangeTariff : AppCompatActivity() {

    companion object{
        const val NUMBER = "NUMBER"
        const val TARIFF = "TARIFF"
        const val BALANCE = "BALANCE"
    }


    private var tariff1_title : TextView? = null
    private var tariff1_price : TextView? = null
    private var tariff2_title : TextView? = null
    private var tariff2_price : TextView? = null
    private var tariff3_title : TextView? = null
    private var tariff3_price : TextView? = null
    private var tariff4_title : TextView? = null
    private var tariff4_price : TextView? = null





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_tariff)
        getSupportActionBar()?.hide()

        tariff1_title = findViewById(R.id.tariff1_title)
        tariff1_price = findViewById(R.id.tariff1_price)
        tariff2_title = findViewById(R.id.tariff2_title)
        tariff2_price = findViewById(R.id.tariff2_price)
        tariff3_title = findViewById(R.id.tariff3_title)
        tariff3_price = findViewById(R.id.tariff3_price)
        tariff4_title = findViewById(R.id.tariff4_title)
        tariff4_price = findViewById(R.id.tariff4_price)

        val tariff_value = intent.getStringExtra(TARIFF)


        if(tariff_value == "Mobile Operator Gold XL"){
            tariff1_title?.text = "Mobile Operator Standart"
            tariff1_price?.text = "100₴"
        }
        else if(tariff_value == "Mobile Operator MAX Energy"){
            tariff2_title?.text = "Mobile Operator Standart"
            tariff2_price?.text = "100₴"
        }
        else if(tariff_value == "Mobile Operator MaxNet Pro"){
            tariff3_title?.text = "Mobile Operator Standart"
            tariff3_price?.text = "100₴"
        }
        else if(tariff_value == "Mobile Operator Smart 4G"){
            tariff4_title?.text = "Mobile Operator Standart"
            tariff4_price?.text = "100₴"
        }

    }

    fun tariff1_Click(view: View) {

        if(tariff1_title?.text == "Mobile Operator Gold XL"){

            val number_value = intent.getStringExtra(NUMBER)
            val balance_value = intent.getStringExtra(BALANCE)

            val intent = Intent(this,GoldXL::class.java)
            intent.putExtra(GoldXL.NUMBER , number_value)
            intent.putExtra(GoldXL.BALANCE , balance_value)
            startActivity(intent)
        }
        else{
            val number_value = intent.getStringExtra(NUMBER)
            val balance_value = intent.getStringExtra(BALANCE)

            val intent = Intent(this,Standart::class.java)
            intent.putExtra(Standart.NUMBER , number_value)
            intent.putExtra(Standart.BALANCE , balance_value)
            startActivity(intent)
        }

    }


    fun tariff2_Click(view: View) {
        if(tariff2_title?.text == "Mobile Operator MAX Energy"){
            val number_value = intent.getStringExtra(NUMBER)
            val balance_value = intent.getStringExtra(BALANCE)

            val intent = Intent(this,MAXEnergy::class.java)
            intent.putExtra(MAXEnergy.NUMBER , number_value)
            intent.putExtra(MAXEnergy.BALANCE , balance_value)
            startActivity(intent)
        }
        else{
            val number_value = intent.getStringExtra(NUMBER)
            val balance_value = intent.getStringExtra(BALANCE)

            val intent = Intent(this,Standart::class.java)
            intent.putExtra(Standart.NUMBER , number_value)
            intent.putExtra(Standart.BALANCE , balance_value)
            startActivity(intent)
        }
    }


    fun tariff3_Click(view: View) {
        if(tariff3_title?.text == "Mobile Operator MaxNet Pro"){
            val number_value = intent.getStringExtra(NUMBER)
            val balance_value = intent.getStringExtra(BALANCE)

            val intent = Intent(this,MaxNetPro::class.java)
            intent.putExtra(MaxNetPro.NUMBER , number_value)
            intent.putExtra(MaxNetPro.BALANCE , balance_value)
            startActivity(intent)
        }
        else{
            val number_value = intent.getStringExtra(NUMBER)
            val balance_value = intent.getStringExtra(BALANCE)

            val intent = Intent(this,Standart::class.java)
            intent.putExtra(Standart.NUMBER , number_value)
            intent.putExtra(Standart.BALANCE , balance_value)
            startActivity(intent)
        }
    }


    fun tariff4_Click(view: View) {
        if(tariff4_title?.text == "Mobile Operator Smart 4G"){
            val number_value = intent.getStringExtra(NUMBER)
            val balance_value = intent.getStringExtra(BALANCE)

            val intent = Intent(this,Smart4G::class.java)
            intent.putExtra(Smart4G.NUMBER , number_value)
            intent.putExtra(Smart4G.BALANCE , balance_value)
            startActivity(intent)
        }
        else{
            val number_value = intent.getStringExtra(NUMBER)
            val balance_value = intent.getStringExtra(BALANCE)

            val intent = Intent(this,Standart::class.java)
            intent.putExtra(Standart.NUMBER , number_value)
            intent.putExtra(Standart.BALANCE , balance_value)
            startActivity(intent)
        }
    }
}