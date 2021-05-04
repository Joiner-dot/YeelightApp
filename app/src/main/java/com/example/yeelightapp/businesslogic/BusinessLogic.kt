package com.example.yeelightapp.businesslogic

import android.R.attr.port
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.widget.SeekBar
import android.widget.ToggleButton
import com.example.yeelightapp.businesslogic.interfaces.IBusinessLogic
import com.example.yeelightapp.database.manager.ManagerDB
import com.example.yeelightapp.lamps.Lamp
import com.example.yeelightapp.lamps.Property
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.BufferedOutputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketTimeoutException


class BusinessLogic(_context: Context?) : IBusinessLogic {
    private val context = _context
    private var isClicked: Boolean = false
    private val managerDB = ManagerDB(context)
    private var mSocket: Socket? = null
    private var activityClass: Activity? = null
    private var cmd_run = true
    private var mBos: BufferedOutputStream? = null
    private var mReader: BufferedReader? = null

    override fun addToDB(name: String?, ip: String?): Boolean {
        return managerDB.addingToDB(name, ip)
    }

    override fun giveAllLamps(): List<Lamp> {
        return managerDB.getInfoFromDB()
    }

    override fun connect(activity: Activity, ip: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val mSocket = Socket()
                mSocket.connect(InetSocketAddress(ip, 55443), 2000)
                if (!mSocket.isConnected) {
                    throw SocketTimeoutException()
                }
                mSocket.keepAlive = true
                mBos = BufferedOutputStream(mSocket!!.getOutputStream())
                mReader = BufferedReader(InputStreamReader(mSocket!!.getInputStream()))
                activityClass = activity
            } catch (e: SocketTimeoutException) {
                Log.d("Socket", e.printStackTrace().toString())
                activity.finish()
            } catch (e: Exception) {
                Log.d("Exception", e.printStackTrace().toString())
            }
        }
    }

    override fun changeRGB(red: Int, green: Int, blue: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val color = (red * 65536) + (green * 256) + blue
                val newVal =
                    "{\"id\":2,\"method\":\"set_rgb\",\"params\":[$color, \"smooth\", 500]}\r\n"
                mBos!!.write(newVal.toByteArray())
                mBos!!.flush()
            } catch (e: Exception) {
                Log.d("Exception", e.printStackTrace().toString())
            }
        }
    }

    override fun changeBrightness(brightness: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                mBos!!.write(("{\"id\":1,\"method\":\"set_bright\",\"params\":[$brightness, \"smooth\", 500]}\r\n").toByteArray())
                mBos!!.flush()
            } catch (e: Exception) {
                Log.d("Exception", e.printStackTrace().toString())
            }
        }
    }

    override fun turnOn() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                Log.d("Button", "ON")
                mBos!!.write(("{\"id\":1,\"method\":\"set_power\",\"params\":[\"on\",\"smooth\",500]}\r\n").toByteArray())
                mBos!!.flush()
            } catch (e: Exception) {
                Log.d("Exception", e.printStackTrace().toString())
            }
        }
    }

    override fun turnOff() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                Log.d("Button", "OFF")
                mBos!!.write(("{\"id\":11,\"method\":\"set_power\",\"params\":[\"off\",\"smooth\",500]}\r\n").toByteArray())
                mBos!!.flush()
            } catch (e: Exception) {
                Log.d("Exception", e.printStackTrace().toString())
            }
        }
    }

    override fun setCurrentRGBB(toggleButton: ToggleButton, vararg seekBar: SeekBar) {
        GlobalScope.launch(Dispatchers.IO) {
            var value: String?
            try {
                while (true) {
                    try {
                        mBos!!.write("{\"id\":5,\"method\":\"get_prop\",\"params\":[\"power\", \"rgb\", \"bright\"]}\r\n".toByteArray())
                        mBos!!.flush()
                        value = mReader!!.readLine()
                        break
                    } catch (e: Exception) {
                    }
                }
                if (value.toString() == "null") {
                    throw NullPointerException()
                }
                Log.d("AAAAAAAAAA", value.toString())
                val gson = Gson().fromJson(value, Property::class.java)
                seekBar[0].progress =
                    Color.red(gson.result[1].toInt())
                seekBar[1].progress =
                    Color.green(gson.result[1].toInt())
                seekBar[2].progress =
                    Color.blue(gson.result[1].toInt())
                seekBar[3].progress =
                    gson.result[2].toInt()
                toggleButton.isChecked = (gson.result[0] == "on")
            } catch (e: NullPointerException) {
                Log.d("TAG", e.printStackTrace().toString())
                activityClass?.finish()
            } catch (e: Exception) {
                Log.d("Exception", e.printStackTrace().toString())
            }
        }
    }

    fun clicked() {
        isClicked = true
    }
}