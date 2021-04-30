package com.example.yeelightapp.businesslogic

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.widget.SeekBar
import com.example.yeelightapp.businesslogic.interfaces.IBusinessLogic
import com.example.yeelightapp.database.manager.ManagerDB
import com.example.yeelightapp.lamps.Lamp
import com.mollin.yapi.YeelightDevice
import com.mollin.yapi.enumeration.YeelightEffect
import com.mollin.yapi.enumeration.YeelightProperty
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception


class BusinessLogic(_context: Context) : IBusinessLogic {
    private val context = _context
    private val managerDB = ManagerDB(context)
    private lateinit var device: YeelightDevice
    override fun addToDB(name: String?, ip: String?) {
        managerDB.addingToDB(name, ip)
    }

    override fun giveAllLamps(): List<Lamp> {
        return managerDB.getInfoFromDB()
    }

    override fun connect(ip: String) {
        GlobalScope.launch {
            val port = 55443
            val effectType = YeelightEffect.SMOOTH
            val effectDuration = 500
            device = YeelightDevice(ip, port, effectType, effectDuration)
        }
    }

    override fun changeRGB(red: Int, green: Int, blue: Int) {
        try {
            GlobalScope.launch {
                delay(200)
                device.setRGB(red, green, blue)
            }
        } catch (e: Exception) {
            Log.d("Exception", e.toString())
        }
    }

    override fun changeBrightness(brightness: Int) {
        try {
            GlobalScope.launch {
                delay(200)
                device.setBrightness(brightness)
            }
        } catch (e: Exception) {
            Log.d("Exception", e.toString())
        }
    }

    override fun turnOn() {
        try {
            GlobalScope.launch {
                delay(200)
                device.setPower(true)
            }
        } catch (e: Exception) {
            Log.d("Exception", e.toString())
        }
    }

    override fun turnOff() {
        try {
            GlobalScope.launch {
                delay(200)
                device.setPower(false)
            }
        } catch (e: Exception) {
            Log.d("Exception", e.toString())
        }
    }

    override fun setCurrentRGBB(vararg seekBar: SeekBar) {
        try {
            GlobalScope.launch {
                delay(200L)
                val color:Int = device.getProperties()[YeelightProperty.RGB]!!.toInt()
                seekBar[0].progress =
                    Color.red(color)
                seekBar[1].progress =
                    Color.green(color)
                seekBar[2].progress =
                    Color.blue(color)
                seekBar[3].progress =
                    device.getProperties()[YeelightProperty.BRIGHTNESS]!!.toInt()
            }
        } catch (e: Exception) {
            Log.d("Exception", e.toString())
        }
    }
}