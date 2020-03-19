package com.ittalent.testitandroid.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ittalent.domain.ItunesSongs
import com.ittalent.testitandroid.R
import com.ittalent.testitandroid.ui.common.*
import com.ittalent.testitandroid.ui.common.DataState.*
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val SONG = "DetailActivity:song"
    }

    private lateinit var component: DetailActivityComponent
    private val viewModel: DetailViewModel by lazy { getViewModel { component.detailViewModel } }
    private lateinit var detailAdapter: DetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        component = app.component.plus(DetailActivityModule(intent.getIntExtra(SONG, -1)))

        viewModel.model.observe(this, Observer(::updateUi))
        viewModel.modelListSongs.observe(this, Observer(::updateListSongs))
        viewModel.findSong()

        rvListSongsAlbum.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)

            detailAdapter = DetailAdapter(){

            }

            rvListSongsAlbum.adapter = detailAdapter
        }


    }

    private fun updateUi(event: Data<ItunesSongs>?) {

        event.with {
            when (dataState) {
                LOADING -> {}
                SUCCESS -> {}
                ERROR -> { }
            }

            data.notNull {
                ivCover.loadUrl(it.artworkUrl100)
                tvAlbumName.text = it.collectionName
                tvSingerName.text = it.artistName
                viewModel.getListSongsAlbum("${it.artistName} + ${it.collectionName}")
            }
        }
    }


    private fun updateListSongs(event: Data<ArrayList<ItunesSongs>>?){
        event.with {
            when (dataState) {
                LOADING -> {

                }
                SUCCESS -> {

                }
                ERROR -> {

                }
            }

            data.notNull {
                detailAdapter.updateListSongs(it)
            }
        }
    }

}
