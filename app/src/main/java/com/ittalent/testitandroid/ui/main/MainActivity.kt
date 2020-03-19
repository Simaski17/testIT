package com.ittalent.testitandroid.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ittalent.domain.ItunesSongs
import com.ittalent.testitandroid.R
import com.ittalent.testitandroid.ui.common.*
import com.ittalent.testitandroid.ui.common.DataState.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var component: MainActivityComponent
    private val viewModel: MainViewModel by lazy { getViewModel { component.mainViewModel } }
    private lateinit var itunesAdapter: ItunesAdapter
    private var offset: Int = 0
    private var ifLoading = true
    private val TAG = "ItunesTest"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        component = app.component.plus(MainActivityModule())

        viewModel.model.observe(this, Observer(::updateUi))
        viewModel.getListSongs("Katy Perry", offset)

        recycler.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 3)

            itunesAdapter = ItunesAdapter() {}
            recycler.adapter = itunesAdapter

            recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0) {
                        val firstVisiblePosition: Int =
                            (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                        var visibleItemCount = (layoutManager as LinearLayoutManager).childCount
                        var totalItemCount = (layoutManager as LinearLayoutManager).itemCount

                        if (ifLoading) {
                            if ((visibleItemCount + firstVisiblePosition) >= totalItemCount) {
                                Log.e(TAG, "End List")
                                ifLoading = false
                                offset += 20
                                viewModel.getListSongs("Katy Perry", offset)
                            }
                        }
                    }
                }
            })
        }


    }

    private fun updateUi(event: Data<ArrayList<ItunesSongs>>?) {

        event.with {
            when (dataState) {
                LOADING -> {
                    if (ifLoading) progress.visibility = VISIBLE else progressNextItems.visibility = VISIBLE
                }
                SUCCESS -> {
                    progress.visibility = GONE
                    progressNextItems.visibility = GONE
                    ifLoading = true
                }
                ERROR -> {
                    progress.visibility = GONE
                    progressNextItems.visibility = GONE
                    ifLoading = true
                }
            }

            data.notNull {
                itunesAdapter.updateListSongs(it)
            }
        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_search, menu)


        val searchItem = menu!!.findItem(R.id.menuSearch)
        val searchView = searchItem?.actionView as SearchView
        searchView.setSubmitButtonEnabled(true)
        searchView.setQueryHint("Buscar...")
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                Log.e("TALENTIT", "newText $newText")
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                Log.e("TALENTIT SEARCH", "newText")
                return true
            }
        })


        return super.onCreateOptionsMenu(menu)
    }

}
