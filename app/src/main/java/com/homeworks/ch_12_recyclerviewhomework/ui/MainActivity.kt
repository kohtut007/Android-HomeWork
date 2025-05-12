package com.homeworks.ch_12_recyclerviewhomework.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.homeworks.ch_12_recyclerviewhomework.adapter.SongAdapter
import com.homeworks.ch_12_recyclerviewhomework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val songList = arrayListOf(
            "Shape Of You",
            "Faded",
            "Hello",
            "Love Yourself",
            "My Name is Skrillex",
            "Blank Space",
            "Stay",
            "Apache",
            "Closer"
        )

        val singerList = arrayListOf(
            "Ed Sheeran",
            "Alan Walker",
            "Adele",
            "Justin Bieber",
            "Skrillex",
            "Taylor Swift",
            "Kid Laroi",
            "Rose",
            "Chainsmokers"
        )

        val adapter = SongAdapter(songList, singerList)
        binding.rvSong.layoutManager = LinearLayoutManager(this)
        binding.rvSong.adapter = adapter
    }
}