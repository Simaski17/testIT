package com.ittalent.data.source

import com.ittalent.domain.ItunesSongs

interface RemoteDataSource {

    suspend fun getListSongs(song: String, offset: Int) : ArrayList<ItunesSongs>

    suspend fun getListSongsAlbum(term: String) : ArrayList<ItunesSongs>

}