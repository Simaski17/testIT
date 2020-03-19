package com.ittalent.testitandroid.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ItunesSongs::class], version = 1)
abstract class SongsDatabase : RoomDatabase() {

    abstract fun songDao(): ItunesSongsDao
}