package com.example.mobileoperator

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mobileoperator.db.MyDbManager
import com.example.mobileoperator.refill.RefillAutorized
import java.text.SimpleDateFormat
import java.util.*

class MainMenu : AppCompatActivity() {

    companion object{
        const val NUMBER = "NUMBER"
        const val BALANCE = "BALANCE"
        const val BALANCE_AFTER_REFILL = "BALANCE_AFTER_REFILL"
        const val NEW_TARIFF = "NEW_TARIFF"


    }


    var date = Calendar.getInstance().time
    var dateFormat = SimpleDateFormat.getDateTimeInstance()
    var formatedDate = dateFormat.format(date)


    val month_format = SimpleDateFormat("dd.MM.yyyy" ,Locale.getDefault())
    val month = month_format.format(date)






    val MyDbManager = MyDbManager(this)

    private var balance_text: TextView? = null
    private var number: TextView? = null
    private var costs: TextView? = null
    private var update: TextView? = null
    private var mb_text: TextView? = null
    private var sms_text: TextView? = null
    private var min_text: TextView? = null
    private var refresh: SwipeRefreshLayout? = null








    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        getSupportActionBar()?.hide()

        balance_text = findViewById(R.id.balance_value)
        number = findViewById(R.id.number_text)
        costs = findViewById(R.id.numeral_text)
        update = findViewById(R.id.update_text)
        mb_text = findViewById(R.id.mb_text)
        sms_text = findViewById(R.id.sms_text)
        min_text = findViewById(R.id.min_text)
        refresh = findViewById(R.id.swipeRefreshLayout)

        MyDbManager.openDatabase()



        date = Calendar.getInstance().time
        dateFormat = SimpleDateFormat.getDateTimeInstance()
        formatedDate = dateFormat.format(date)


        Values.year = month[8].toString() + month[9].toString()
        Values.month = month[3].toString() + month[4].toString()


        update?.text = "Обновлено $formatedDate"


        val balance_value = intent.getStringExtra(BALANCE)
        val number_value = intent.getStringExtra(NUMBER)
        val balance_after_refill_value = intent.getStringExtra(BALANCE_AFTER_REFILL)

        if( !Values.is_autorized ) {
            Autorization(number_value ,balance_value)
            balance_text?.text = "$balance_value₴"
            Values.is_autorized = true


        }
        else if(Values.isChangedTariff){

            balance_text?.text = "$balance_value₴"
            Values.isChangedTariff = false


        }
        else {
            balance_text?.text = "$balance_after_refill_value₴"

        }


        showNumber(number_value)
        showDescriptionTariff(number_value)

        refresh?.setOnRefreshListener {

            android.os.Handler().postDelayed({

                date = Calendar.getInstance().time
                dateFormat = SimpleDateFormat.getDateTimeInstance()
                formatedDate = dateFormat.format(date)
                update?.text = "Обновлено $formatedDate"
                refresh?.isRefreshing = false

            } ,2000)


        }


    }


    override fun onSaveInstanceState(outState: Bundle) {

        val balance_value = intent.getStringExtra(BALANCE)
        val number_value = intent.getStringExtra(NUMBER)

        outState.run {
            putString("balance" ,balance_value)
            putString("number" ,number_value)
        }

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        balance_text?.text  = savedInstanceState.getString("balance")
        number?.text  = savedInstanceState.getString("number")

    }





    fun Autorization(Number: String? ,Balance: String?){


        val numberAutorised = MyDbManager.readAutorizedUser("number")

        var number_is_autorized = false


        for(i in numberAutorised){
            if(i == Number ){
                number_is_autorized = true
            }
        }

        if(number_is_autorized == false){


            val name_list  = MyDbManager.readNewAbonentRequest("name")
            val surname_list  = MyDbManager.readNewAbonentRequest("surname")
            val number_list  = MyDbManager.readNewAbonentRequest("number")
            val tariff_list  = MyDbManager.readNewAbonentRequest("tariff")
            val number_list_reg  = MyDbManager.readRegistration("number")
            val password_list  = MyDbManager.readRegistration("password")


            var indexOfNumber = 0
            indexOfNumber = number_list.indexOf(Number)

            var indexOfNumberReg = 0
            indexOfNumberReg = number_list_reg.indexOf(Number)



            val name  = name_list[indexOfNumber]
            val surname  = surname_list[indexOfNumber]
            val number  = number_list[indexOfNumber]
            val tariff  = tariff_list[indexOfNumber]
            val password  = password_list[indexOfNumberReg]

            MyDbManager.writeAutorizedUser(
                "$name" ,
                "$surname" ,
                "$number" ,
                "$password" ,
                "$tariff" ,
                "$Balance"
            )
        }

        showDescriptionTariff(Number)
    }

    override fun onBackPressed() {}


    fun showNumber(number_value: String?) {

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



    fun showDescriptionTariff(Number: String?){

        val tariff_value = intent.getStringExtra(NEW_TARIFF)

        if(tariff_value == null) {

            val number_list = MyDbManager.readNewAbonentRequest("number")
            val tariff_list = MyDbManager.readNewAbonentRequest("tariff")

            var indexOfNumber = 0
            indexOfNumber = number_list.indexOf(Number)

            val tariff = tariff_list[indexOfNumber]

            if (tariff == "Mobile Operator Gold XL") {

                mb_text?.text = "10 из 10 ГБ"
                sms_text?.text = "100 из 100 шт"
                min_text?.text = "200 из 200 мин"

            } else if (tariff == "Mobile Operator MAX Energy") {

                mb_text?.text = "4 из 4 ГБ"
                sms_text?.text = "500 из 500 шт"
                min_text?.text = "300 из 300 мин"

            } else if (tariff == "Mobile Operator MaxNet Pro") {

                mb_text?.text = "20 из 20 ГБ"
                sms_text?.text = "30 из 30 шт"
                min_text?.text = "60 из 60 мин"

            } else if (tariff == "Mobile Operator Smart 4G") {

                mb_text?.text = "8 из 8 ГБ"
                sms_text?.text = "120 из 120 шт"
                min_text?.text = "500 из 500 мин"

            }
        }
        else{
            if (tariff_value == "Mobile Operator Gold XL") {

                mb_text?.text = "10 из 10 ГБ"
                sms_text?.text = "100 из 100 шт"
                min_text?.text = "200 из 200 мин"

            } else if (tariff_value == "Mobile Operator MAX Energy") {

                mb_text?.text = "4 из 4 ГБ"
                sms_text?.text = "500 из 500 шт"
                min_text?.text = "300 из 300 мин"

            } else if (tariff_value == "Mobile Operator MaxNet Pro") {

                mb_text?.text = "20 из 20 ГБ"
                sms_text?.text = "30 из 30 шт"
                min_text?.text = "60 из 60 мин"

            } else if (tariff_value == "Mobile Operator Smart 4G") {

                mb_text?.text = "8 из 8 ГБ"
                sms_text?.text = "120 из 120 шт"
                min_text?.text = "500 из 500 мин"

            } else if (tariff_value == "Mobile Operator Standart") {

                mb_text?.text = "4 из 4 ГБ"
                sms_text?.text = "50 из 50 шт"
                min_text?.text = "120 из 120 мин"

            }

        }

    }

    fun Refill_Click(view: View) {
        val number_value = intent.getStringExtra(NUMBER)

        val intent = Intent(this ,RefillAutorized::class.java)
        intent.putExtra(RefillAutorized.NUMBER ,number_value)
        startActivity(intent)
    }

    fun menu_Click(view: View) {
        val number_value = intent.getStringExtra(NUMBER)


        val number_list  = MyDbManager.readNewAbonentRequest("number")
        val tariff_list  = MyDbManager.readNewAbonentRequest("tariff")
        val balance_list  = MyDbManager.readNewAbonentRequest("balance")


        var indexOfNumber = 0
        indexOfNumber = number_list.indexOf(number_value)

        val tariff = tariff_list[indexOfNumber]
        val balance = balance_list[indexOfNumber]




        val intent = Intent(this ,Menu::class.java)
        intent.putExtra(Menu.NUMBER ,number_value)
        intent.putExtra(Menu.TARIFF ,tariff)
        intent.putExtra(Menu.BALANCE ,balance)
        startActivity(intent)

    }




}