package com.ittalent.data.repository

import com.ittalent.data.source.RemoteDataSource
import com.ittalent.domain.ItunesSongs

class ItunesRepository(private val remoteDataSource: RemoteDataSource) {

    suspend fun getListSongs(song: String, offset: Int): ArrayList<ItunesSongs> {
        return remoteDataSource.getListSongs(song, offset)
    }

}