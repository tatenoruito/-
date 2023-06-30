package com.example.tachapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //(1)idを取得　(idで、viewを取得する)
        val tx :TextView = findViewById(R.id.tv)
        val btn1 : Button = findViewById(R.id.btnSend)
        val btn2 : Button = findViewById(R.id.btnReturn)
        val btn3 : Button = findViewById(R.id.btnClear)

        //(2)クリック処理
        btn1.setOnClickListener {
            //押したときの処理
            tx.text = "送る"
        }
        btn2.setOnClickListener {
            tx.text = "戻る"
        }
        btn3.setOnClickListener {
            tx.text = "・・・"
        }
    }

}