package com.example.yeelightapp.activities

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.yeelightapp.R
import com.example.yeelightapp.businesslogic.manager.ManagerBL
import com.example.yeelightapp.database.manager.ManagerDB

class NewLampPage: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.adding_new_lamp)
        val managerBL = ManagerBL(baseContext)
        val name:EditText = findViewById(R.id.nameOfNewLamp)
        val ip:EditText = findViewById(R.id.idNewLamp)
        val select: Button = findViewById(R.id.select)
        select.setOnClickListener {
            managerBL.addToDB(name.text.toString(), ip.text.toString())
        }
    }
}