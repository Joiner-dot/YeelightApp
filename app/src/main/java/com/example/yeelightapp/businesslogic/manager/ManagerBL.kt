package com.example.yeelightapp.businesslogic.manager

import android.app.Activity
import android.content.Context
import android.text.BoringLayout
import android.widget.Button
import android.widget.SeekBar
import android.widget.Switch
import android.widget.ToggleButton
import androidx.appcompat.widget.SwitchCompat
import com.example.yeelightapp.businesslogic.BusinessLogic
import com.example.yeelightapp.businesslogic.manager.interfaces.IManagerBL
import com.example.yeelightapp.lamps.Lamp

class ManagerBL(_context: Context?) : IManagerBL {
    private val context = _context
    private val businessLogic = BusinessLogic(context)
    override fun addToDB(name: String?, ip: String?): Boolean {
        return businessLogic.addToDB(name, ip)
    }

    override fun giveAllLamps(): List<Lamp> {
        return businessLogic.giveAllLamps()
    }

    override fun connect(activity: Activity, ip: String) {
        businessLogic.connect(activity, ip)
    }


    override fun changeRGB(red: Int, green: Int, blue: Int) {
        if (red == 0 && green == 0 && blue == 0) {
            businessLogic.changeRGB(1, 1, 1)
        } else {
            businessLogic.changeRGB(red, green, blue)
        }
    }

    override fun changeBrightness(brightness: Int) {
        if (brightness == 0) {
            businessLogic.changeBrightness(1)
        } else {
            businessLogic.changeBrightness(brightness)
        }
    }

    override fun turnOn() {
        businessLogic.turnOn()
    }

    override fun turnOff() {
        businessLogic.turnOff()
    }

    override fun setCurrentRGBB(toggleButton: ToggleButton, vararg seekBar: SeekBar) {
        businessLogic.setCurrentRGBB(toggleButton, *seekBar)
    }


    fun clicked() {
        businessLogic.clicked()
    }
}