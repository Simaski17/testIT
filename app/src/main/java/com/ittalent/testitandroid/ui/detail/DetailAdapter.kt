package com.ittalent.testitandroid.ui.detail

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ittalent.domain.ItunesSongs
import com.ittalent.testitandroid.R
import com.ittalent.testitandroid.ui.common.inflate
import kotlinx.android.synthetic.main.view_songs_album.view.*

class DetailAdapter(private val listener: (ItunesSongs) -> Unit) : RecyclerView.Adapter<DetailAdapter.ViewHolder>()  {

    private var listOfSongsAlbum = arrayListOf<ItunesSongs>()

    fun updateListSongs(songs: ArrayList<ItunesSongs>) {
        this.listOfSongsAlbum.addAll(songs)
        notifyDataSetChanged()
        Log.e("LIST","COUNT "+this.listOfSongsAlbum.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailAdapter.ViewHolder {
        val view = parent.inflate(R.layout.view_songs_album, false)
        return DetailAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.listOfSongsAlbum.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = listOfSongsAlbum[position]
        holder.bind(song)
        holder.itemView.setOnClickListener { listener(song) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(song: ItunesSongs) {
            itemView.tvSongsAlbumTitle.text = song.trackName
        }
    }



}