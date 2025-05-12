package com.homeworks.ch_11_listviewhomework.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.homeworks.ch_11_listviewhomework.R

class SongAdapter(val songList: List<String>, val singerList: List<String>) : BaseAdapter() {
    override fun getCount() = songList.size

    override fun getItem(position: Int): Any? = songList[position]

    override fun getItemId(position: Int): Long = position.toLong()
    override fun getView(
        position: Int,
        view: View?,
        parent: ViewGroup?,
    ): View? {
        return view ?: LayoutInflater.from(parent?.context)
            .inflate(R.layout.list_item_song, parent, false).apply {
                findViewById<TextView>(R.id.tvSongName).text = songList[position]
                findViewById<TextView>(R.id.tvSingerName).text = singerList[position]
                findViewById<ImageView>(R.id.ivFavorite).setOnClickListener {
                    val isFavorite = it.tag as? Boolean ?: false
                    it.tag = !isFavorite
                    val imageView = it as ImageView
                    if (isFavorite) {
                        imageView.setColorFilter(context.getColor(R.color.gray))
                    } else {
                        imageView.setColorFilter(context.getColor(R.color.red))
                    }
                }
            }
    }
}