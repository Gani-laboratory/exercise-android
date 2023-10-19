package com.reyuki.latihan

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var tvResult: TextView
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == MoveForResultActivity.RESULT_CODE && result.data != null) {
            val selectedValue =
                result.data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0)
            tvResult.text = "Hasil: $selectedValue"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveActivity: Button = findViewById(R.id.btn_mv_activity)
        btnMoveActivity.setOnClickListener(this)

        val btnMoveActivityWithData: Button = findViewById(R.id.btn_mv_activity_data)
        btnMoveActivityWithData.setOnClickListener(this)

        val btnMoveWithObject: Button = findViewById(R.id.btn_mv_activity_object)
        btnMoveWithObject.setOnClickListener(this)

        val btnDialPhone: Button = findViewById(R.id.btn_dial_num)
        btnDialPhone.setOnClickListener(this)

        val btnMovForResult: Button = findViewById(R.id.btn_mv_for_result)
        btnMovForResult.setOnClickListener(this)

        tvResult = findViewById(R.id.tv_result)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_mv_activity -> {
                val moveIntent = Intent(this@MainActivity, MoveActivity::class.java)
                startActivity(moveIntent)
            }

            R.id.btn_mv_activity_data -> {
                val moveWithDataActivity =
                    Intent(this@MainActivity, MoveWithDataActivity::class.java)
                moveWithDataActivity.putExtra(MoveWithDataActivity.EXTRA_NAME, "Acnologia")
                moveWithDataActivity.putExtra(MoveWithDataActivity.EXTRA_AGE, 5)
                startActivity(moveWithDataActivity)
            }

            R.id.btn_mv_activity_object -> {
                val person = Person(
                    "HeheBois",
                    5,
                    "bois@m.c",
                    "Tokyo",
                )
                val moveWithObjActivity =
                    Intent(this@MainActivity, MoveWithObjectActivity::class.java)
                moveWithObjActivity.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person)
                startActivity(moveWithObjActivity)
            }

            R.id.btn_dial_num -> {
                val phoneNumber = "081210841382"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }

            R.id.btn_mv_for_result -> {
                val movForResultIntent = Intent(this@MainActivity, MoveForResultActivity::class.java)
                resultLauncher.launch(movForResultIntent)
            }
        }
    }
}