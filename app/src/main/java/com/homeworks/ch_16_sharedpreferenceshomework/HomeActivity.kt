package com.homeworks.ch_16_sharedpreferenceshomework

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.homeworks.ch_16_sharedpreferenceshomework.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogout.setOnClickListener {
            getSharedPreferences("LOGIN_PREF", MODE_PRIVATE).edit { clear() }
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}