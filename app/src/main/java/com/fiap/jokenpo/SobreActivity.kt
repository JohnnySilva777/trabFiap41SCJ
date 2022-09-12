package com.fiap.jokenpo

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_sobre.*

class SobreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sobre)

        val imageView: ImageView = findViewById(R.id.imageViewLogoNet)

        val url = "https://cdn-icons-png.flaticon.com/512/4144/4144475.png"
        Picasso.get().load(url).into(imageView);

        btnFechar.setOnClickListener {
            finish()
        }
    }
}
