package com.example.yeelightapp.businesslogic.interfaces

import android.widget.SeekBar
import com.example.yeelightapp.lamps.Lamp
import com.mollin.yapi.YeelightDevice

interface IBusinessLogic {

    fun addToDB(name: String?, ip: String?)

    fun giveAllLamps(): List<Lamp>

    fun connect(ip: String)

    fun changeRGB(red: Int, green: Int, blue: Int)

    fun changeBrightness(brightness: Int)

    fun turnOn()

    fun turnOff()

    fun setCurrentRGBB(vararg seekBar: SeekBar)
}