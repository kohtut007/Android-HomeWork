package com.homeworks.ch_11_listviewhomework

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.homeworks.ch_11_listviewhomework.adapter.SongAdapter
import com.homeworks.ch_11_listviewhomework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val songList = arrayListOf<String>(
            "Shape Of Your",
            "Faded",
            "Hello",
            "Love Yourself",
            "My Name is Skrillex",
            "Blank Space",
            "Stay",
            "Apache",
            "Closer"

        )

        val singerList = arrayListOf<String>(
            "Ed Sheeran",
            "Alan Walker",
            "Adele",
            "Justin Bieber",
            "Skrillex",
            "Taylor Swift",
            "Kid Laroi",
            "Rose",
            "Chains Smokers",
        )

        val adapter = SongAdapter(songList = songList, singerList = singerList)
        binding.lvNote.adapter = adapter

    }
}