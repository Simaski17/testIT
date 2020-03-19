package com.ittalent.testitandroid.data.database

import com.ittalent.data.source.LocalDataSource
import com.ittalent.domain.ItunesSongs
import com.ittalent.testitandroid.data.toDomainSong
import com.ittalent.testitandroid.data.toRoomSong
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(db: SongsDatabase): LocalDataSource {

    private val songDao = db.songDao()

    override suspend fun isEmpty(): Boolean =
    withContext(Dispatchers.IO) { songDao.songCount() <= 0 }

    override suspend fun saveSongs(songs: List<ItunesSongs>) {
        withContext(Dispatchers.IO) { songDao.insertSongs(songs = songs.map { it.toRoomSong() }) }
    }

    override suspend fun getPopularSongs(): List<ItunesSongs> = withContext(Dispatchers.IO) {
        songDao.getAll().map { it.toDomainSong() }
    }

    override suspend fun findById(id: Int): ItunesSongs  = withContext(Dispatchers.IO) {
        songDao.findById(id).toDomainSong()
    }

    override suspend fun update(song: ItunesSongs) {
        withContext(Dispatchers.IO) { songDao.updateSong(song = song.toRoomSong()) }
    }

}