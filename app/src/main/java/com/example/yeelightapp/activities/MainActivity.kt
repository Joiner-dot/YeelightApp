package com.example.yeelightapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.yeelightapp.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar!!.hide();
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listLamps: Button = findViewById(R.id.listOfLampsMain)
        val newLamp:Button = findViewById(R.id.addNewLamp)
        newLamp.setOnClickListener {
            val intent = Intent(this, NewLampPage::class.java)
            startActivity(intent)
        }

        listLamps.setOnClickListener {
            val intent = Intent(this, ChoosingLampPage::class.java)
            startActivity(intent)
        }
    }
}