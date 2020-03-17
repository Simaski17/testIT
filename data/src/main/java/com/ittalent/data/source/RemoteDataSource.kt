package com.ittalent.data.source

import com.ittalent.domain.ItunesSongs

interface RemoteDataSource {

    suspend fun getListSongs(song: String) : List<ItunesSongs>

}