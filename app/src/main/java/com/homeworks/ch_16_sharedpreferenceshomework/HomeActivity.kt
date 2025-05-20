package com.homeworks.ch_16_sharedpreferenceshomework

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.homeworks.ch_16_sharedpreferenceshomework.databinding.ActivityHomeBinding
import androidx.core.content.edit

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogout.setOnClickListener {
            getSharedPreferences("login", MODE_PRIVATE).edit() {
                clear()
            }
            startActivity(Intent(this, LoginActivity::class.java)).apply { finish() }
        }
    }
}