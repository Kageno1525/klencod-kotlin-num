package com.num.nomo

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.TextView
import android.view.View
import java.util.Random

class MainActivity : Activity() {

    private lateinit var neonCore: View
    private lateinit var tvTitle: TextView
    private lateinit var gradientDrawable: GradientDrawable
    private var colorAnimator: ValueAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        neonCore = findViewById(R.id.neonCore)
        tvTitle = findViewById(R.id.tvTitle)
        val btnGlitch = findViewById<Button>(R.id.btnGlitch)
        val btnRgb = findViewById<Button>(R.id.btnRgb)
        val rootLayout = findViewById<View>(R.id.rootLayout)

        // 1. تحويل الـ View لدائرة نيون في الكود
        gradientDrawable = GradientDrawable()
        gradientDrawable.shape = GradientDrawable.OVAL
        gradientDrawable.setColor(Color.parseColor("#00FFFF")) // لون مبدئي سماوي
        gradientDrawable.setStroke(8, Color.WHITE) // إطار أبيض
        neonCore.background = gradientDrawable

        // 2. أنيميشن "التنفس" (Pulse) بيشتغل أوتوماتيك
        val pulseX = ObjectAnimator.ofFloat(neonCore, "scaleX", 1f, 1.3f, 1f).apply {
            duration = 1500
            repeatCount = ObjectAnimator.INFINITE
            interpolator = android.view.animation.AccelerateDecelerateInterpolator()
        }
        val pulseY = ObjectAnimator.ofFloat(neonCore, "scaleY", 1f, 1.3f, 1f).apply {
            duration = 1500
            repeatCount = ObjectAnimator.INFINITE
            interpolator = android.view.animation.AccelerateDecelerateInterpolator()
        }
        pulseX.start()
        pulseY.start()

        // 3. أنيميشن تغيير الألوان المستمر للقلب (RGB)
        colorAnimator = ValueAnimator.ofObject(ArgbEvaluator(), 
            Color.parseColor("#00FFFF"), // سماوي
            Color.parseColor("#FF00FF"), // موف فاتح
            Color.parseColor("#39FF14"), // أخضر نيون
            Color.parseColor("#00FFFF")  // رجوع للسماوي
        ).apply {
            duration = 4000
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener { animator ->
                gradientDrawable.setColor(animator.animatedValue as Int)
            }
        }
        colorAnimator?.start()

        // 4. زرار الرجة (Glitch Effect)
        btnGlitch.setOnClickListener {
            // رجة سريعة للشاشة
            val shake = ObjectAnimator.ofFloat(rootLayout, "translationX", 0f, 25f, -25f, 15f, -15f, 5f, -5f, 0f).apply {
                duration = 400
            }
            // رجة عكسية للقلب
            val coreShake = ObjectAnimator.ofFloat(neonCore, "translationY", 0f, -30f, 30f, -15f, 15f, 0f).apply {
                duration = 400
            }
            shake.start()
            coreShake.start()
            
            // نغير لون العنوان فجأة
            tvTitle.setTextColor(Color.RED)
            tvTitle.postDelayed({ tvTitle.setTextColor(Color.parseColor("#00FFFF")) }, 300)
        }

        // 5. زرار تغيير لون الخلفية (Madness)
        btnRgb.setOnClickListener {
            val random = Random()
            val randomColor = Color.rgb(random.nextInt(100), random.nextInt(100), random.nextInt(100))
            // تغيير خلفية الشاشة للون غامق عشوائي
            rootLayout.setBackgroundColor(randomColor)
            
            // نكسر الأنيميشن الحالي ونعمله واحد جديد بلون عشوائي
            colorAnimator?.cancel()
            val newColor1 = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256))
            val newColor2 = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256))
            
            colorAnimator = ValueAnimator.ofObject(ArgbEvaluator(), newColor1, newColor2, newColor1).apply {
                duration = 2000
                repeatCount = ValueAnimator.INFINITE
                addUpdateListener { animator ->
                    gradientDrawable.setColor(animator.animatedValue as Int)
                }
            }
            colorAnimator?.start()
        }
    }
}