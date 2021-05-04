package com.example.yeelightapp.database.interfaces

import com.example.yeelightapp.lamps.Lamp

interface IDataBase {

    fun insertNewLamp(vararg values: String):Boolean

    fun selectAllLamps(): List<Lamp>
}