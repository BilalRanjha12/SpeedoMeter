package com.leeddev.speedometer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var ivHome:ImageView=findViewById(R.id.iv_home)
        var button:Button=findViewById(R.id.button)
        button.setOnClickListener {
            ivHome.animate().rotationX(100f).duration = 100
        }
    }
}