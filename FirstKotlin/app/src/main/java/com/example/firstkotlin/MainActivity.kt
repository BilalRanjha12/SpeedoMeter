package com.example.firstkotlin

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnclickme = findViewById<Button>(R.id.btn_click_me)
        val textview = findViewById<TextView>(R.id.counter)
        var timesclicked = 0
        var mediaPlayer: MediaPlayer? = null
        btnclickme.setOnClickListener {
            mediaPlayer = MediaPlayer.create(this, R.raw.button)
            mediaPlayer?.start()
            timesclicked += 1
            textview.text = timesclicked.toString()
            Toast.makeText(this, "Hi i am first message", Toast.LENGTH_SHORT).show()
        }
    }
}