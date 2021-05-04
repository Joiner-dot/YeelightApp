package com.example.yeelightapp.businesslogic.interfaces

import android.app.Activity
import android.widget.SeekBar
import android.widget.Switch
import android.widget.ToggleButton
import androidx.appcompat.widget.SwitchCompat
import com.example.yeelightapp.lamps.Lamp
import com.mollin.yapi.YeelightDevice

interface IBusinessLogic {

    fun addToDB(name: String?, ip: String?):Boolean

    fun giveAllLamps(): List<Lamp>

    fun connect(activity: Activity, ip: String)

    fun changeRGB(red: Int, green: Int, blue: Int)

    fun changeBrightness(brightness: Int)

    fun turnOn()

    fun turnOff()

    fun setCurrentRGBB(toggleButton: ToggleButton, vararg seekBar: SeekBar)
}