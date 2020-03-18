package com.ittalent.testitandroid.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ittalent.domain.ItunesSongs
import com.ittalent.testitandroid.R
import com.ittalent.testitandroid.ui.common.inflate
import com.ittalent.testitandroid.ui.common.loadUrl
import kotlinx.android.synthetic.main.view_songs.view.*

class ItunesAdapter(var songs: List<ItunesSongs>, private val listener: (ItunesSongs) -> Unit) : RecyclerView.Adapter<ItunesAdapter.ViewHolder>() {

//    var movies: List<ItunesSongs> by basicDiffUtil(
//        emptyList(),
//        areItemsTheSame = { old, new -> old.id == new.id }
//    )
//
//    fun setDays(barHour: ArrayList<String>) {
//        this.barHour = ArrayList()
//        this.barHour = barHour
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_songs, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.songs.size
    }

    override fun onBindViewHolder(holder: ItunesAdapter.ViewHolder, position: Int) {
        val song = songs[position]
        holder.bind(song)
        holder.itemView.setOnClickListener { listener(song) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(song: ItunesSongs) {
            itemView.songTitle.text = song.trackName
            itemView.songCover.loadUrl(song.artworkUrl100)

//            itemView.movieTitle.text = movie.title
//            itemView.movieCover.loadUrl("https://image.tmdb.org/t/p/w185/${movie.posterPath}")
        }
    }

}