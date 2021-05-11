package com.example.mobileoperator.tariffs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.mobileoperator.Values
import com.example.mobileoperator.MainMenu
import com.example.mobileoperator.R
import com.example.mobileoperator.db.MyDbManager

class Smart4G : AppCompatActivity() {

    companion object{
        const val NUMBER = "NUMBER"
        const val BALANCE = "BALANCE"
    }

    val MyDbManager = MyDbManager(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_smart4_g)
        getSupportActionBar()?.hide()
        MyDbManager.openDatabase()
    }

    fun change_Click(view: View) {

        val number_value = intent.getStringExtra(GoldXL.NUMBER)
        val balance_value = intent.getStringExtra(GoldXL.BALANCE)



        val number_list  = MyDbManager.readNewAbonentRequest("number")



        val new_tariff = "Mobile Operator Smart 4G"
        var indexOfNumber = 0
        indexOfNumber = number_list.indexOf(number_value)



        Toast.makeText(this, "Изменено успешно!", Toast.LENGTH_LONG).show()

        MyDbManager.ChangeTariff(new_tariff , indexOfNumber + 1)



        Values.isChangedTariff = true
        val intent = Intent(this,MainMenu::class.java)
        intent.putExtra(MainMenu.NEW_TARIFF , new_tariff)
        intent.putExtra(MainMenu.BALANCE , "$balance_value")
        intent.putExtra(MainMenu.NUMBER , "$number_value")
        startActivity(intent)


    }

}