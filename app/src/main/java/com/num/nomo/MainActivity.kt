package com.num.nomo

import android.os.Bundle
import android.app.Activity // استبدلنا AppCompat بـ Activity الأساسي
import android.widget.Button
import android.widget.TextView

class MainActivity : Activity() { // التغيير هنا

    private var counter = 0
    private lateinit var tvCounter: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCounter = findViewById(R.id.tvCounter)
        val btnIncrease = findViewById<Button>(R.id.btnIncrease)
        val btnDecrease = findViewById<Button>(R.id.btnDecrease)
        val btnReset = findViewById<Button>(R.id.btnReset)

        btnIncrease.setOnClickListener {
            counter++
            tvCounter.text = counter.toString()
        }

        btnDecrease.setOnClickListener {
            counter--
            tvCounter.text = counter.toString()
        }

        btnReset.setOnClickListener {
            counter = 0
            tvCounter.text = counter.toString()
        }
    }
}