package com.leeddev.speedometer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        var btnGetStarted:Button=findViewById(R.id.btn_get_started)
     btnGetStarted.animate().rotation(120f).setDuration(300)

    btnGetStarted.setOnClickListener(){
     val intent=  Intent(this,MainActivity::class.java)
    startActivity(intent)
    }
}
}