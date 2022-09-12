package com.fiap.jokenpo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private val delayTela = 3500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        carregar()
    }

    fun carregar(){
        val anim = AnimationUtils.loadAnimation(this,R.anim.animacao_splash)
        ivFotoSplash!!.clearAnimation()
        ivFotoSplash!!.startAnimation(anim)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this,MenuActivity::class.java)
            startActivity(intent)
            finish()
        },delayTela)
    }
}
