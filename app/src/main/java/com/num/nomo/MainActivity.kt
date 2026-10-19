package com.example.counterapp // (سيب اسم الباكدج اللي موجود عندك زي ما هو)

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // 1. تعريف المتغيرات
    private var counter = 0
    private lateinit var tvCounter: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // ربط الشاشة بالكود

        // 2. ربط عناصر الشاشة بالكود عن طريق الـ ID
        tvCounter = findViewById(R.id.tvCounter)
        val btnIncrease = findViewById<Button>(R.id.btnIncrease)
        val btnDecrease = findViewById<Button>(R.id.btnDecrease)
        val btnReset = findViewById<Button>(R.id.btnReset)

        // 3. برمجة زرار الزيادة
        btnIncrease.setOnClickListener {
            counter++ // زيادة الرقم بواحد
            tvCounter.text = counter.toString() // تحديث النص على الشاشة
        }

        // 4. برمجة زرار التقليل
        btnDecrease.setOnClickListener {
            counter-- // تقليل الرقم بواحد
            tvCounter.text = counter.toString()
        }

        // 5. برمجة زرار التصفير
        btnReset.setOnClickListener {
            counter = 0 // إرجاع الرقم صفر
            tvCounter.text = counter.toString()
        }
    }
}