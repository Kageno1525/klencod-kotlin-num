package com.num.nomo

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.BounceInterpolator
import android.widget.Button
import java.util.Random

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // تعريف العناصر
        val magicBall = findViewById<View>(R.id.magicBall)
        val btnRotate = findViewById<Button>(R.id.btnRotate)
        val btnBounce = findViewById<Button>(R.id.btnBounce)
        val btnColor = findViewById<Button>(R.id.btnColor)

        // 1. أنيميشن الدوران (ObjectAnimator)
        btnRotate.setOnClickListener {
            val rotateAnim = ObjectAnimator.ofFloat(magicBall, "rotation", 0f, 360f)
            rotateAnim.duration = 1000 // ثانية واحدة
            rotateAnim.start()
        }

        // 2. أنيميشن التنطيط (Bounce)
        btnBounce.setOnClickListener {
            // بنخلي الكرة تنزل تحت شوية الأول عشان تدي إحساس إنها بتستعد للنط
            val bounceAnim = ObjectAnimator.ofFloat(magicBall, "translationY", 0f, -300f, 0f)
            bounceAnim.duration = 1200
            // هنا السحر: بنستخدم BounceInterpolator عشان الحركة تبقى طبيعية زي الكورة بالظبط
            bounceAnim.interpolator = BounceInterpolator()
            bounceAnim.start()
        }

        // 3. أنيميشن تغيير اللون (ValueAnimator)
        btnColor.setOnClickListener {
            // بنجيب لون عشوائي جديد
            val random = Random()
            val colorFrom = (magicBall.backgroundTintList?.defaultColor) ?: Color.RED
            val colorTo = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256))

            // ValueAnimator بيتحكم في القيم من لون للتاني بسلاسة
            val colorAnim = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
            colorAnim.duration = 500
            colorAnim.addUpdateListener { animator ->
                // بنحدث لون الكرة في كل فريم من الأنيميشن
                magicBall.backgroundTintList = android.content.res.ColorStateList.valueOf(animator.animatedValue as Int)
            }
            colorAnim.start()
        }
    }
}