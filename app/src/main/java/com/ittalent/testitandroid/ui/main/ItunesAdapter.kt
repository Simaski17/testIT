package com.ittalent.testitandroid.ui.main

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ittalent.domain.ItunesSongs
import com.ittalent.testitandroid.R
import com.ittalent.testitandroid.ui.common.inflate
import com.ittalent.testitandroid.ui.common.loadUrl
import kotlinx.android.synthetic.main.view_songs.view.*

class ItunesAdapter(private val listener: (ItunesSongs) -> Unit) : RecyclerView.Adapter<ItunesAdapter.ViewHolder>() {

    private var listOfSongs = arrayListOf<ItunesSongs>()

    fun updateListSongs(songs: ArrayList<ItunesSongs>) {
        this.listOfSongs.addAll(songs)
        notifyDataSetChanged()
        Log.e("LIST","COUNT "+this.listOfSongs.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_songs, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.listOfSongs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = listOfSongs[position]
        holder.bind(song)
        holder.itemView.setOnClickListener { listener(song) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(song: ItunesSongs) {
            itemView.songTitle.text = song.trackName
            itemView.songCover.loadUrl(song.artworkUrl100)
        }
    }

}