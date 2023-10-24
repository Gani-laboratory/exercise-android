package com.reyuki.latihan

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.reyuki.latihan.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Hero>("DATA", Hero::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Hero>("DATA")
        }

        binding.tvItemName.text = data?.name
        binding.tvDescription.text = data?.description
        binding.imgItemPhoto.setImageResource(data!!.photo)
    }
}