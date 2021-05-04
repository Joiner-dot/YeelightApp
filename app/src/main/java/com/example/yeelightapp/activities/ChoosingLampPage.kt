package com.example.yeelightapp.activities

import android.app.ActionBar
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.yeelightapp.R
import com.example.yeelightapp.businesslogic.manager.ManagerBL


class ChoosingLampPage : Activity() {
    val params: LinearLayout.LayoutParams =
        LinearLayout.LayoutParams(
            ActionBar.LayoutParams.MATCH_PARENT,
            ActionBar.LayoutParams.WRAP_CONTENT
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_of_lamps)
    }

    override fun onResume() {
        super.onResume()
        val managerBL = ManagerBL(baseContext)
        val linearLayout: LinearLayout = findViewById(R.id.listofLamps)
        linearLayout.removeAllViews()
        val lamps = managerBL.giveAllLamps()
        params.setMargins(0, 50, 0, 0)
        for (i in lamps.indices) {
            val textView = TextView(this)
            textView.text = lamps[i].name
            textView.isClickable = true
            textView.textSize = 26f
            textView.gravity = Gravity.CENTER
            textView.layoutParams = params
            textView.setTextColor(Color.parseColor("#000000"))
            textView.setPadding(10, 10, 10, 10)
            textView.background = ContextCompat.getDrawable(baseContext, R.drawable.border)
            textView.setOnClickListener {
                val intent = Intent(this, MenuPage::class.java)
                intent.putExtra("IP", lamps[i].ip)
                startActivity(intent)
            }
            linearLayout.addView(textView)
        }
    }
}
