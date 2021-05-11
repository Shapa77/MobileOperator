package com.example.mobileoperator.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns


class MyDbManager(context: Context) {

    val NewAbonentRequestMyDbHelper = NewAbonentRequestMyDbHelper(context)
    val RegistrationMyDbHelper = RegistrationMyDbHelper(context)
    val AutorizedUserMuDbHelper = AutorizedUserMuDbHelper(context)

    var NewAbonentdb:SQLiteDatabase? = null
    var Registerdb:SQLiteDatabase? = null
    var Aitorizedb:SQLiteDatabase? = null

    fun openDatabase(){
        Registerdb = RegistrationMyDbHelper.writableDatabase
        NewAbonentdb = NewAbonentRequestMyDbHelper.writableDatabase
        Aitorizedb = AutorizedUserMuDbHelper.writableDatabase
    }

    fun CloseDatabase(){
        NewAbonentRequestMyDbHelper.close()
        RegistrationMyDbHelper.close()
        AutorizedUserMuDbHelper.close()

    }



    fun writeOnNewAbonentRequest(Name :String , Surname: String, Number: String, Tariff: String,Balance: String){
        val values = ContentValues().apply {
            put(NewAbonentRequestDb.COLUMN_NAME_NAME, Name)
            put(NewAbonentRequestDb.COLUMN_NAME_SURNAME, Surname)
            put(NewAbonentRequestDb.COLUMN_NAME_NUMBER, Number)
            put(NewAbonentRequestDb.COLUMN_NAME_TARIFF, Tariff)
            put(NewAbonentRequestDb.COLUMN_NAME_BALANCE, Balance)
        }

        NewAbonentdb?.insert(NewAbonentRequestDb.TABLE_NAME,null, values)
    }

    fun writeOnRegistration(Number :String , Password: String){
        val values = ContentValues().apply {
            put(RegistrationDb.COLUMN_NAME_NUMBER, Number)
            put(RegistrationDb.COLUMN_NAME_PASSWORD, Password)
        }

        Registerdb?.insert(RegistrationDb.TABLE_NAME,null, values)
    }

    fun writeAutorizedUser(Name :String , Surname: String, Number: String, Password: String ,Tariff: String , Balance : String){

        val values = ContentValues().apply {
            put(AutorizedUserDb.COLUMN_NAME_NAME, Name)
            put(AutorizedUserDb.COLUMN_NAME_SURNAME, Surname)
            put(AutorizedUserDb.COLUMN_NAME_NUMBER, Number)
            put(AutorizedUserDb.COLUMN_NAME_PASSWORD, Password)
            put(AutorizedUserDb.COLUMN_NAME_TARIFF, Tariff)
            put(AutorizedUserDb.COLUMN_NAME_BALANCE, Balance)
        }

        Aitorizedb?.insert(AutorizedUserDb.TABLE_NAME,null, values)
    }



    fun readNewAbonentRequest(value:String): ArrayList<String> {

        val dataList = ArrayList<String>()
        val cursor = NewAbonentdb?.query(NewAbonentRequestDb.TABLE_NAME,null,null,null,null,null,null)

        with(cursor){
            while(this?.moveToNext()!!){

                if(value == "name"){
                    val name_text = cursor?.getString(cursor.getColumnIndex(NewAbonentRequestDb.COLUMN_NAME_NAME))
                    dataList.add(name_text.toString())
                }
                else if(value == "surname"){
                    val surname_text = cursor?.getString(cursor.getColumnIndex(NewAbonentRequestDb.COLUMN_NAME_SURNAME))
                    dataList.add(surname_text.toString())
                }
                else if(value == "tariff"){
                    val tariff_text = cursor?.getString(cursor.getColumnIndex(NewAbonentRequestDb.COLUMN_NAME_TARIFF))
                    dataList.add(tariff_text.toString())
                }
                else if (value == "number"){
                    val number_text = cursor?.getString(cursor.getColumnIndex(NewAbonentRequestDb.COLUMN_NAME_NUMBER))
                    dataList.add(number_text.toString())
                }
                else if (value == "balance"){
                    val balance_text = cursor?.getString(cursor.getColumnIndex(NewAbonentRequestDb.COLUMN_NAME_BALANCE))
                    dataList.add(balance_text.toString())
                }
            }
        }

        cursor?.close()

        return dataList
    }

    fun readRegistration(value: String): ArrayList<String> {
        val dataList = ArrayList<String>()
        val cursor = Registerdb?.query(RegistrationDb.TABLE_NAME,null,null,null,null,null,null)

        with(cursor){
            while(this?.moveToNext()!!){

                if(value == "number"){
                    val number_text_reg = cursor?.getString(cursor.getColumnIndex(RegistrationDb.COLUMN_NAME_NUMBER))
                    dataList.add(number_text_reg.toString())
                }
                else if(value == "password"){
                    val password_text = cursor?.getString(cursor.getColumnIndex(RegistrationDb.COLUMN_NAME_PASSWORD))
                    dataList.add(password_text.toString())
                }

            }
        }

        cursor?.close()

        return dataList
    }

    fun readAutorizedUser(value: String): ArrayList<String> {

        val dataList = ArrayList<String>()
        val cursor = Aitorizedb?.query(AutorizedUserDb.TABLE_NAME,null,null,null,null,null,null)

        with(cursor){
            while(this?.moveToNext()!!){

                if(value == "name"){
                    val name_text = cursor?.getString(cursor.getColumnIndex(AutorizedUserDb.COLUMN_NAME_NAME))
                    dataList.add(name_text.toString())
                }
                else if(value == "surname"){
                    val surname_text = cursor?.getString(cursor.getColumnIndex(AutorizedUserDb.COLUMN_NAME_SURNAME))
                    dataList.add(surname_text.toString())
                }
                else if(value == "number"){
                    val number_text = cursor?.getString(cursor.getColumnIndex(AutorizedUserDb.COLUMN_NAME_NUMBER))
                    dataList.add(number_text.toString())
                }
                else if(value == "password"){
                    val password_text = cursor?.getString(cursor.getColumnIndex(AutorizedUserDb.COLUMN_NAME_PASSWORD))
                    dataList.add(password_text.toString())
                }
                else if(value == "tariff"){
                    val tariff_text = cursor?.getString(cursor.getColumnIndex(AutorizedUserDb.COLUMN_NAME_TARIFF))
                    dataList.add(tariff_text.toString())
                }
                else if(value == "balance"){
                    val balance_text = cursor?.getString(cursor.getColumnIndex(AutorizedUserDb.COLUMN_NAME_BALANCE))
                    dataList.add(balance_text.toString())
                }

            }
        }

        cursor?.close()

        return dataList

    }



    fun onUpgrate(){
        NewAbonentRequestMyDbHelper.onUpgrade(db = NewAbonentdb, oldVersion = VersionDatabase.VERSION, newVersion = VersionDatabase.VERSION + 1)
        RegistrationMyDbHelper.onUpgrade(db = Registerdb, oldVersion = VersionDatabase.VERSION, newVersion = VersionDatabase.VERSION + 1)
        AutorizedUserMuDbHelper.onUpgrade(db = Aitorizedb, oldVersion = VersionDatabase.VERSION, newVersion = VersionDatabase.VERSION + 1)
        }



    fun Refill(Balance: String,  id:Int){

            val selection = BaseColumns._ID + "=$id"
            val values_new = ContentValues().apply {
            put(NewAbonentRequestDb.COLUMN_NAME_BALANCE,Balance)
        }

        NewAbonentdb?.update(NewAbonentRequestDb.TABLE_NAME,values_new,selection,null)

    }

    fun ChangeTariff(Tariff:String,  id:Int){

        val selection = BaseColumns._ID + "=$id"
        val values_new = ContentValues().apply {
            put(NewAbonentRequestDb.COLUMN_NAME_TARIFF,Tariff)
        }

        NewAbonentdb?.update(NewAbonentRequestDb.TABLE_NAME,values_new,selection,null)
    }

}