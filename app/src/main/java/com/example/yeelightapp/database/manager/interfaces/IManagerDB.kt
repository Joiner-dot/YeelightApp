package com.example.yeelightapp.database.manager.interfaces

import com.example.yeelightapp.lamps.Lamp

interface IManagerDB {

    fun addingToDB(name: String?, ip: String?):Boolean
    fun getInfoFromDB(): List<Lamp>
}