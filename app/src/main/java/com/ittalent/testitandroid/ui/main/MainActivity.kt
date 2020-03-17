package com.ittalent.testitandroid.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import com.ittalent.testitandroid.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
                Log.e("TALENTIT","newText $newText")
                return true
            }
            override fun onQueryTextSubmit(query: String): Boolean {
                Log.e("TALENTIT SEARCH","newText")
                return true
            }
        })


        return super.onCreateOptionsMenu(menu)
    }

}
