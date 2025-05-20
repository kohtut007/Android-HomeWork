package com.homeworks.ch_16_sharedpreferenceshomework

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.homeworks.ch_16_sharedpreferenceshomework.databinding.ActivityLoginBinding
import androidx.core.content.edit

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            getSharedPreferences("login", MODE_PRIVATE).edit() {
                putBoolean("isLogin", true)
            }
            startActivity(Intent(this, HomeActivity::class.java)).apply {
                finish()
            }
        }
    }
}