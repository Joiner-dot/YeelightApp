package com.example.yeelightapp.activities

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.ToggleButton
import com.example.yeelightapp.R
import com.example.yeelightapp.businesslogic.manager.ManagerBL


class MenuPage : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)
    }

    override fun onResume() {
        super.onResume()
        val ip = intent.getSerializableExtra("IP")
        val managerBL = ManagerBL(baseContext)
        managerBL.connect(this, ip.toString())
        val red: SeekBar = findViewById(R.id.red)
        val green: SeekBar = findViewById(R.id.green)
        val blue: SeekBar = findViewById(R.id.blue)
        val brightness: SeekBar = findViewById(R.id.brightness)
        val onOff: ToggleButton = findViewById(R.id.onOff)
        managerBL.setCurrentRGBB(onOff, red, green, blue, brightness)
        red.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                managerBL.clicked()
                managerBL.changeRGB(red.progress, green.progress, blue.progress)
                Log.d("TAGGG", "Red")
            }
        })
        green.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                managerBL.clicked()
                managerBL.changeRGB(red.progress, green.progress, blue.progress)
                Log.d("TAGGG", "Green")
            }
        })
        blue.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                managerBL.clicked()
                managerBL.changeRGB(red.progress, green.progress, blue.progress)
                Log.d("TAGGG", "Blue")
            }
        })
        brightness.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                managerBL.clicked()
                managerBL.changeBrightness(brightness.progress)
                Log.d("TAGGG", "Brightness")
            }
        })
        onOff.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                managerBL.turnOn()
            } else {
                managerBL.turnOff()
            }
        }
    }
}