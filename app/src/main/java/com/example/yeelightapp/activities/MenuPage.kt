package com.example.yeelightapp.activities

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import com.example.yeelightapp.R
import com.example.yeelightapp.businesslogic.manager.ManagerBL

class MenuPage : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)
        val ip = intent.getSerializableExtra("IP")
        val managerBL = ManagerBL(baseContext)
        managerBL.connect(ip.toString())
        val red: SeekBar = findViewById(R.id.red)
        val green: SeekBar = findViewById(R.id.green)
        val blue: SeekBar = findViewById(R.id.blue)
        val brightness: SeekBar = findViewById(R.id.brightness)
        managerBL.setCurrentRGBB(red, green, blue, brightness)

        red.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                managerBL.changeRGB(red.progress, green.progress, blue.progress)
            }
        })
        green.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                managerBL.changeRGB(red.progress, green.progress, blue.progress)
            }
        })
        blue.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                managerBL.changeRGB(red.progress, green.progress, blue.progress)
            }
        })
        brightness.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                managerBL.changeBrightness(brightness.progress)
            }
        })
    }
}