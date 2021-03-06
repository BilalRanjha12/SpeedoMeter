package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etInput: EditText = findViewById(R.id.et_input)
        val btnStart: Button = findViewById(R.id.btn_start)
        btnStart.setOnClickListener {
            if (etInput.text.isEmpty()) {
                Toast.makeText(
                    this,
                    "Name is mandatory", Toast.LENGTH_LONG
                ).show()
            } else {
                val intent = Intent(this, QuizQuestionActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}