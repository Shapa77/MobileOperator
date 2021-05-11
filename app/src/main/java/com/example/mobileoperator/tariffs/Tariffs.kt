package com.example.mobileoperator.tariffs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mobileoperator.R
import com.example.mobileoperator.db.MyDbManager

class Tariffs : AppCompatActivity() {


    companion object{
        const val NUMBER = "NUMBER"
        const val TARIFF = "TARIFF"
        const val BALANCE = "BALANCE"
    }



    val MyDbManager = MyDbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tariffs)
        getSupportActionBar()?.hide();

        MyDbManager.openDatabase()

    }

    fun myTariff_Click(view: View) {


        val number_value = intent.getStringExtra(NUMBER)
        val tariff_value = intent.getStringExtra(TARIFF)

        val intent = Intent(this,MyTariff::class.java)
        intent.putExtra(MyTariff.NUMBER , number_value)
        intent.putExtra(MyTariff.TARIFF , tariff_value)
        intent.putExtra(ChangeTariff.TARIFF , tariff_value)
        startActivity(intent)

    }

    fun changeTariff_Click(view: View) {



        val tariff_value = intent.getStringExtra(TARIFF)
        val number_value = intent.getStringExtra(NUMBER)
        val balance_value = intent.getStringExtra(BALANCE)


        val intent = Intent(this,ChangeTariff::class.java)
        intent.putExtra(ChangeTariff.NUMBER , number_value)
        intent.putExtra(ChangeTariff.TARIFF , tariff_value)
        intent.putExtra(ChangeTariff.BALANCE , balance_value)
        startActivity(intent)

    }



}