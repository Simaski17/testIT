package com.ittalent.testitandroid.data.server

import com.ittalent.data.source.RemoteDataSource
import com.ittalent.domain.ItunesSongs

class TheItunesDbDataSource(private val theItunesDbService: TheItunesDbService) : RemoteDataSource {

    override suspend fun getListSongs(song: String): List<ItunesSongs> =
    theItunesDbService.listSongsAsync(song).execute().body()!!.results
}