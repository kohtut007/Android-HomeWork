package com.homeworks.ch_16_sharedpreferenceshomework

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.homeworks.ch_16_sharedpreferenceshomework.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            getSharedPreferences("LOGIN_PREF", MODE_PRIVATE).edit {
                putBoolean("isLogin", true)
            }
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
}