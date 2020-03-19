package com.ittalent.data.source

import com.ittalent.domain.ItunesSongs

interface LocalDataSource {

    suspend fun isEmpty(): Boolean
    suspend fun saveSongs(songs: List<ItunesSongs>)
    suspend fun getPopularSongs(): List<ItunesSongs>
    suspend fun findById(id: Int): ItunesSongs
    suspend fun update(song: ItunesSongs)

}