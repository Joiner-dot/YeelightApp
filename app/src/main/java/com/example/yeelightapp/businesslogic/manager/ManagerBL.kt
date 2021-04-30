package com.example.yeelightapp.businesslogic.manager

import android.content.Context
import android.widget.SeekBar
import com.example.yeelightapp.businesslogic.BusinessLogic
import com.example.yeelightapp.businesslogic.manager.interfaces.IManagerBL
import com.example.yeelightapp.lamps.Lamp

class ManagerBL(_context: Context) : IManagerBL {
    private val context = _context
    private val businessLogic = BusinessLogic(context)
    override fun addToDB(name: String?, ip: String?) {
        businessLogic.addToDB(name, ip)
    }

    override fun giveAllLamps(): List<Lamp> {
        return businessLogic.giveAllLamps()
    }

    override fun connect(ip: String) {
        businessLogic.connect(ip)
    }

    override fun changeRGB(red: Int, green: Int, blue: Int) {
        businessLogic.changeRGB(red, green, blue)
    }

    override fun changeBrightness(brightness: Int) {
        businessLogic.changeBrightness(brightness)
    }

    override fun turnOn() {
        businessLogic.turnOn()
    }

    override fun turnOff() {
        businessLogic.turnOff()
    }

    override fun setCurrentRGBB(vararg seekBar: SeekBar) {
        businessLogic.setCurrentRGBB(*seekBar)
    }
}