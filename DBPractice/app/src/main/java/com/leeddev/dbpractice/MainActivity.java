package com.leeddev.dbpractice;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
   private Dbhelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbhelper=new Dbhelper(this);
        dbhelper.insertContact("32.4","33","234");
        Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();

    }

}