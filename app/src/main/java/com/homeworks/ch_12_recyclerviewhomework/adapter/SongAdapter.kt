package com.homeworks.ch_12_recyclerviewhomework.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.homeworks.ch_12_recyclerviewhomework.R

class SongAdapter(
    private val songList: List<String>,
    private val singerList: List<String>,
) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_song, parent, false)
        return SongViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.tvSongName.text = songList[position]
        holder.tvSingerName.text = singerList[position]
        holder.ivFavourite.setOnClickListener {
            val isFavorite = it.tag as? Boolean ?: false
            it.tag = !isFavorite
            val imageView = it as ImageView
            if (isFavorite) {
                imageView.setColorFilter(it.context.getColor(R.color.gray))
            } else {
                imageView.setColorFilter(it.context.getColor(R.color.red))
            }
        }
    }

    override fun getItemCount(): Int = songList.size

    class SongViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvSongName: TextView = view.findViewById(R.id.tvSongName)
        val tvSingerName: TextView = view.findViewById(R.id.tvSingerName)
        val ivFavourite: ImageView = view.findViewById(R.id.ivFavorite)
    }
}