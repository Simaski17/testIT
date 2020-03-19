package com.ittalent.data.repository

import com.ittalent.data.source.LocalDataSource
import com.ittalent.data.source.RemoteDataSource
import com.ittalent.domain.ItunesSongs

class ItunesRepository(private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource) {

    suspend fun getListSongs(song: String, offset: Int): ArrayList<ItunesSongs> {
        val songs = remoteDataSource.getListSongs(song, offset)
        localDataSource.saveSongs((songs as List<ItunesSongs>?)!!)
        return songs
    }

    suspend fun findById(id: Int): ItunesSongs = localDataSource.findById(id)

    suspend fun getListSongsAlbum(term: String): ArrayList<ItunesSongs> {
        return remoteDataSource.getListSongsAlbum(term)
    }


}