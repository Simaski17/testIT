package com.ittalent.testitandroid.data.server

import com.ittalent.data.source.RemoteDataSource
import com.ittalent.domain.ItunesSongs

class TheItunesDbDataSource(private val theItunesDbService: TheItunesDbService) : RemoteDataSource {

    override suspend fun getListSongs(song: String, offset: Int): ArrayList<ItunesSongs> =
    theItunesDbService.listSongsAsync(song, offset).execute().body()!!.results

    override suspend fun getListSongsAlbum(term: String): ArrayList<ItunesSongs> =
    theItunesDbService.listSongsAlbumAsync(term).execute().body()!!.results
}