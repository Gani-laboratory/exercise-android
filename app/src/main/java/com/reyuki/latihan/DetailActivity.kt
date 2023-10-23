package com.reyuki.latihan

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imgPhoto: ImageView = findViewById(R.id.img_item_photo)
        val tvName: TextView = findViewById(R.id.tv_item_name)
        val tvDesc: TextView = findViewById(R.id.tv_description)

        val data = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Hero>("DATA", Hero::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Hero>("DATA")
        }

        tvName.text = data?.name
        tvDesc.text = data?.description
        imgPhoto.setImageResource(data!!.photo)
    }
}