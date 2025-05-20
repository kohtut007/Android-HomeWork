package com.homeworks.ch_16_sharedpreferenceshomework

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.homeworks.ch_16_sharedpreferenceshomework.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE)

        lifecycleScope.launch {
            delay(3000)
            startActivity(
                if (sharedPreferences.getBoolean("isLogin", false)) {
                    Intent(this@SplashScreenActivity, HomeActivity::class.java)
                } else {
                    Intent(this@SplashScreenActivity, LoginActivity::class.java)
                }
            ).apply {
                finish()
            }
        }
    }
}