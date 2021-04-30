package com.example.yeelightapp.database.manager

import android.content.Context
import com.example.yeelightapp.database.DataBase
import com.example.yeelightapp.database.manager.interfaces.IManagerDB
import com.example.yeelightapp.lamps.Lamp

class ManagerDB(_context: Context) : IManagerDB {
    private val context = _context
    private val db: DataBase = DataBase(context)
    override fun addingToDB(name: String?, ip: String?) {
        if (name != null && name != "" && ip != null && ip != "") {
            db.insertNewLamp(name, ip)
        }
    }

    override fun getInfoFromDB(): List<Lamp> {
        return db.selectAllLamps()
    }
}