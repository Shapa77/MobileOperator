package com.example.mobileoperator.new_abonent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.mobileoperator.MainActivity
import com.example.mobileoperator.R
import com.example.mobileoperator.db.MyDbManager

class NewAbonentRequest : AppCompatActivity() {

    var Tariffs = emptyArray<String>()

    private var get_number_button: View? = null
    private var home_page_text: TextView? = null
    private var tariffs: Spinner? = null
    private var new_name: TextView? = null
    private var new_surname: TextView? = null


    val MyDbManager = MyDbManager(this)

    var RandNubmer: String? = null

    val default_balance = 00.00



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_abonent_request)
        getSupportActionBar()?.hide();

        MyDbManager.openDatabase()


        Tariffs += "Mobile Operator Standart"
        Tariffs += "Mobile Operator MAX Energy"
        Tariffs += "Mobile Operator Smart 4G"
        Tariffs += "Mobile Operator MaxNet Pro"
        Tariffs += "Mobile Operator Gold XL"


        get_number_button = findViewById(R.id.recall_button)
        tariffs = findViewById(R.id.tariffs)
        home_page_text = findViewById(R.id.home_page_text)
        new_name = findViewById(R.id.phone_number)
        new_surname = findViewById(R.id.value)


        val adapter = ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item , Tariffs)
        tariffs?.adapter = adapter



    }


    fun RandNumber(){
        RandNubmer ="38074" + (999999..10000000).random().toString()
    }

    fun get_number_button_Click(view: View){

        if(new_name?.text.toString() == "" || new_surname?.text.toString() == "") {
            Toast.makeText(this, "Введите все данные", Toast.LENGTH_LONG).show()
        }
        else {

            RandNumber()

            var tariff_text: String = tariffs?.getSelectedItem().toString()

            MyDbManager.writeOnNewAbonentRequest(new_name?.text.toString(), new_surname?.text.toString(), RandNubmer.toString(), tariff_text, default_balance.toString())

            val intent = Intent(this,NewAbonentYes::class.java)
            intent.putExtra(NewAbonentYes.NEW_NUMBER , RandNubmer)
            startActivity(intent)
            MyDbManager.CloseDatabase()

        }

    }



    fun home_page_text_Click(view: View){
        MyDbManager.CloseDatabase()
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }


}

