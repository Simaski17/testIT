package com.ittalent.testitandroid.data.database

import androidx.room.*

@Dao
interface ItunesSongsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSongs(songs: List<ItunesSongs>)

    @Query("SELECT * FROM ItunesSongs")
    fun getAll(): List<ItunesSongs>

    @Query("SELECT * FROM itunessongs WHERE trackId = :trackId")
    fun findById(trackId: Int): ItunesSongs

    @Query("SELECT COUNT(trackId) FROM ItunesSongs")
    fun songCount(): Int

    @Update
    fun updateSong(song: ItunesSongs)

}